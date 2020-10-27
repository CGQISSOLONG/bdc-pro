package com.admin.controller.admin;


import com.admin.aop.LogAopAnnotation;
import com.admin.controller.admin.dto.TreeDto;
import com.admin.controller.admin.vo.TreeVo;
import com.admin.dao.primary.AdminMenuDao;
import com.admin.dao.primary.AdminRoleDao;
import com.admin.entity.primary.AdminRole;
import com.admin.service.AdminRoleService;
import com.bdc.common.Constants;
import com.bdc.util.UrlConstant;
import net.sf.json.JSONArray;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


/**
 * @author 创建人：laill
 * @version 版本号：V1.0
 * <p>
 * **************************修订记录***************************************
 * <p>
 * 2019年06月05日 laill 创建该类功能。
 * <p>
 * ***********************************************************************
 * </p>
 * @ClassName 类名：
 * @Description 功能说明：
 * <p>
 * TODO
 * </p>
 * ***********************************************************************
 * @date 创建日期：2019年06月03日
 */
@Controller
@RequestMapping("/role")
public class AdminRoleController {

    @Autowired
    private AdminRoleService roleService;

    @Autowired
    private AdminRoleDao adminRoleDao;


    @Autowired
    private AdminMenuDao adminMenuDao;

    /**
     * 加载列表
     *
     * @param model
     * @return
     */
    @GetMapping(UrlConstant.LIST)
    public String list(Model model) {
        List<AdminRole> list=roleService.list();
        model.addAttribute("list", list);
        return "admin/role/list";
    }

    /**
     * 角色新增
     *
     * @param role
     * @return
     */
    @PostMapping(UrlConstant.CREATE)
    @LogAopAnnotation
    public String create(AdminRole role) {
        final Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        role.setCreateTime(dateString);
        role.setUpdateTime(dateString);
        role.setStatus(Constants.ROLE_STATUS_DISABLE);
        adminRoleDao.save(role);
        return "redirect:/role/list";
    }

    @GetMapping(UrlConstant.FORM)
    @LogAopAnnotation
    public String form(@RequestParam(value = "roleId", required = false) Integer roleId, Model model) {
        String api = "/role/create";
        if (roleId != null) {
            model.addAttribute("role", adminRoleDao.findOne(roleId));
            api = "/role/" + roleId + "/update";
        }
        model.addAttribute("api", api);

        return "admin/role/form";
    }

    /**
     * 角色更新
     *
     * @param id
     * @param role
     * @return
     */
    @PostMapping(value = "/{id}" + UrlConstant.UPDATE)
    @LogAopAnnotation
    public String modify(@PathVariable("id") Integer id, AdminRole role) {
        AdminRole roleTemp = adminRoleDao.findOne(id);
        final Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        roleTemp.setUpdateTime(dateString);
        roleTemp.setRoleName(role.getRoleName());
        roleTemp.setDescription(role.getDescription());
        roleService.modify(roleTemp);
        return "redirect:/role/list";
    }

    @PutMapping(value = "/{id}" + UrlConstant.TOGGLESTATUS)
    @ResponseBody
    @LogAopAnnotation
    public void switchStatus(@PathVariable("id") Integer id, @RequestParam("status") Integer state) {
        if (id == null || state == null) {
            return;
        }

        roleService.updateStateById(id, state);
    }

    @DeleteMapping(value = "/{id}" + UrlConstant.DELETE)
    @ResponseBody
    @LogAopAnnotation
    public void delete(@PathVariable("id") Integer id) {
        roleService.delete(id);
    }


    /**
     * 获取角色具有的菜单和未分配的菜单
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = "/{id}/select-menu")
    public String selectMenu(@PathVariable("id") Integer id, Model model) {
        //角色已分配的菜单ids
        final List<Integer> menuIdsByRoleId = adminMenuDao.findMenuIdsByRoleId(id);
        model.addAttribute("menuIds",  StringUtils.join(menuIdsByRoleId, ","));
        //获取所有的菜单
        final List<TreeDto> list = adminMenuDao.findAllMenuList();
        Iterator<TreeDto> iterable=list.iterator();
        List<TreeVo> menuList= Lists.newArrayList();
        while (iterable.hasNext()){
            TreeDto treeDto=iterable.next();
            final TreeVo treeVo = new TreeVo();
            treeVo.setId(treeDto.getId());
            //特别注意，z-tree需要pId，PId不能正确显示树型结构，在实体做处理
            treeVo.setpId(treeDto.getParent().getId()!=null? treeDto.getParent().getId():0);
            treeVo.setName(treeDto.getLabel());
            menuList.add(treeVo);
        }
        final JSONArray jsonArray = JSONArray.fromObject(menuList);
        model.addAttribute("list", jsonArray);
        model.addAttribute("roleId", id);
        model.addAttribute("api", "/role/" + id + "/grant-menu");
        return "admin/role/grant-menu";
    }

    /**
     * 为角色分配菜单
     *
     * @param id
     * @param menuIds
     * @return
     */
    @PostMapping(value = "/{id}/grant-menu")
    @LogAopAnnotation
    public String grantMenu(@PathVariable("id") Integer id, String menuIds) {
        Integer[] ids=null;
        if (StringUtils.isBlank(menuIds)) {
            ids = new Integer[0];
        }else{
            String[] st=menuIds.split(",");
            ids = new Integer[st.length];
            for (int i=0; i < st.length; i++) {
                ids[i] = Integer.valueOf(st[i]);
            }
        }
        roleService.grantMenu(id, Arrays.asList(ids));
        return "redirect:/role/list";
    }

    @GetMapping(value = "/{id}/select-resource")
    public String selectRole(@PathVariable("id") String id, Model model) {
        model.addAttribute("list", roleService.selectResources(id));
        model.addAttribute("api", "/role/" + id + "/grant-resource");
        return "admin/role/grant-resource";
    }

    /**
     * 为角色分配资源
     *
     * @param id
     * @param rid
     * @return
     */
    @PostMapping(value = "/{id}/grant-resource")
    @LogAopAnnotation
    public String grantResources(@PathVariable("id") String id, String[] rid) {
        if (rid == null) {
            rid = new String[0];
        }
        roleService.grantResource(id, Arrays.asList(rid));
        return "redirect:/role";
    }

}
