package com.admin.controller.admin;


import com.admin.aop.LogAopAnnotation;
import com.admin.controller.admin.req.AdminMenuReq;
import com.admin.dao.primary.AdminMenuDao;
import com.admin.entity.primary.AdminMenu;
import com.admin.service.AdminMenuService;
import com.bdc.common.base.BaseResult;
import com.bdc.common.enums.ResultEnum;
import com.bdc.util.BeanCopyUtils;
import com.bdc.util.ResultUtil;
import com.bdc.util.UrlConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/menu")
public class AdminMenuController {

    @Autowired
    private AdminMenuDao adminMenuDao;

    @Autowired
    private AdminMenuService adminMenuService;

    /**
     * 新增菜单
     *
     * @param adminMenuReq
     * @return
     */
    @PostMapping(UrlConstant.CREATE)
    @LogAopAnnotation
    public String create(AdminMenuReq adminMenuReq) {
        AdminMenu adminMenu = BeanCopyUtils.copyProperties(adminMenuReq, AdminMenu.class);
        adminMenu.setRank(adminMenuReq.getParentIds().split(",").length);
        adminMenuService.create(adminMenu);
        return "redirect:/menu/list";
    }

    /**
     * 加载菜单
     *
     * @param model
     * @return
     */
    @GetMapping(UrlConstant.LIST)
    public String list(Model model) {
        model.addAttribute("list", adminMenuService.list());
        return "admin/menu/list";
    }

    /**
     * 添加菜单或者更新菜单
     *
     * @param menuId
     * @param parent
     * @param model
     * @return
     */
    @GetMapping(value = "/form")
    @LogAopAnnotation
    public String form(@RequestParam(value = "menuId", required = false) String menuId,
                       @RequestParam(value = "parent", required = false) boolean parent,
                       @RequestParam(value = "parentId", required = false) Integer parentId,
                       Model model) {
        String url = null;
        if (menuId != null && !parent) {
            final AdminMenu one = adminMenuDao.findOne(Integer.valueOf(menuId));
            model.addAttribute("menu", one);
            model.addAttribute("url", one.getRank() == 1 ? false : true);
            url = "/menu/" + menuId + UrlConstant.UPDATE;
        } else {
            url = "/menu" + UrlConstant.CREATE;
            if (menuId != null) {
                model.addAttribute("parentPath", menuId);
                model.addAttribute("parentId", parentId);
                model.addAttribute("url", true);
            }
        }
        model.addAttribute("api", url);
        return "admin/menu/form";
    }

    /**
     * 更新菜单
     *
     * @param id
     * @param adminMenuReq
     * @return
     */
    @PostMapping(value = "/{id}" + UrlConstant.UPDATE)
    @LogAopAnnotation
    public String modify(@PathVariable("id") Integer id, AdminMenuReq adminMenuReq) {
        AdminMenu adminMenu = adminMenuDao.findOne(id);
        adminMenu.setLabel(adminMenuReq.getLabel());
        adminMenu.setUrl(adminMenuReq.getUrl());
        adminMenu.setOrderNum(adminMenuReq.getOrderNum());
        adminMenuService.modify(adminMenu);
        return "redirect:/menu/list";
    }


    @PutMapping(value = "/{id}" + UrlConstant.TOGGLESTATUS)
    @ResponseBody
    @LogAopAnnotation
    public void switchStatus(@PathVariable("id") Integer id, @RequestParam("disable") String disable) {
        if (id == null || disable == null) {
            return;
        }
        adminMenuService.updateStateById(id, disable);

    }

    @DeleteMapping(value = "/{id}" + UrlConstant.DELETE)
    @ResponseBody
    @LogAopAnnotation
    public BaseResult delete(@PathVariable("id") Integer id) {
        try {
            adminMenuService.delete(id);
            return ResultUtil.success();
        } catch (Exception e) {
            return ResultUtil.error(ResultEnum.DELETE_ERROR);
        }

    }
}
