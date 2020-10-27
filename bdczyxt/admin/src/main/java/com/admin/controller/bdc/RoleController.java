package com.admin.controller.bdc;

import com.admin.aop.LogAopAnnotation;
import com.admin.controller.admin.vo.TreeVo;
//import com.zhenshan.repository.primary.RoleRepository;
import com.bdc.common.Constants;
import com.bdc.dao.MenuDao;
import com.bdc.dao.RoleDao;
import com.bdc.entity.Role;
import com.bdc.entity.bdcTree;
import com.bdc.service.RoleService;
import com.bdc.util.UrlConstant;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @ClassName 类名：HjptAuthorityController
 * @Description 功能说明：汇聚平台角色权限管理
 */
@Controller
@RequestMapping("/hjpt/role")
@Slf4j
public class RoleController {


    @Autowired
    private RoleDao roleDao;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuDao menuDao;

    @GetMapping(UrlConstant.LIST)
    public String list(Model model) {
        model.addAttribute("list", roleDao.findAll());
        return "bdc/role/list";
    }

    /**
     * 角色新增
     *
     * @param role
     * @return
     */
    @PostMapping(UrlConstant.CREATE)
    @LogAopAnnotation
    public String create(Role role) {
        final Date date = new Date();
        role.setCreateTime(date);
        role.setUpdateTime(date);
        role.setState(Constants.ROLE_STATUS_DISABLE);
        roleService.save(role);
        return "redirect:/hjpt/role/list";
    }

    @GetMapping(UrlConstant.FORM)
    @LogAopAnnotation
    public String form(@RequestParam(value = "roleId", required = false) Integer roleId, Model model) {
        String api = "/hjpt/role/create";
        if (roleId != null) {
            model.addAttribute("role", roleDao.findOne(roleId));
            api = "/hjpt/role/" + roleId + "/update";
        }
        model.addAttribute("api", api);

        return "bdc/role/form";
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
    public String modify(@PathVariable("id") Integer id, Role role) {
        Role roleTemp = roleDao.findOne(id);
        roleTemp.setUpdateTime(new Date());
        roleTemp.setRoleName(role.getRoleName());
        roleTemp.setDescription(role.getDescription());
        roleService.save(roleTemp);
        return "redirect:/hjpt/role/list";
    }

    @PutMapping(value = "/{id}" + UrlConstant.TOGGLESTATUS)
    @ResponseBody
    @LogAopAnnotation
    public void switchStatus(@PathVariable("id") Integer id, @RequestParam("status") Integer state) {
        if (id == null || state == null) {
            return;
        }
        roleDao.updateStateById(id, state);
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
        final List<Integer> menuIdsByRoleId = menuDao.findMenuIdsByRoleId(id);
        model.addAttribute("menuIds", StringUtils.join(menuIdsByRoleId, ","));
        //获取所有的菜单
        final List<bdcTree> list = menuDao.findAllMenuList();
        Iterator<bdcTree> iterable = list.iterator();
        List<TreeVo> menuList = Lists.newArrayList();
        while (iterable.hasNext()) {
            bdcTree treeDto = iterable.next();
            final TreeVo treeVo = new TreeVo();
            treeVo.setId(treeDto.getId());
            //特别注意，z-tree需要pId，PId不能正确显示树型结构，在实体做处理
            treeVo.setpId(treeDto.getParent().getId() != null ? treeDto.getParent().getId() : 0);
            treeVo.setName(treeDto.getLabel());
            menuList.add(treeVo);
        }
        final JSONArray jsonArray = JSONArray.fromObject(menuList);
        model.addAttribute("list", jsonArray);
        model.addAttribute("roleId", id);
        model.addAttribute("api", "/hjpt/role/" + id + "/grant-menu");
        return "bdc/role/grant-menu";
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
        Integer[] ids = null;
        if (StringUtils.isBlank(menuIds)) {
            ids = new Integer[0];
        } else {
            String[] st = menuIds.split(",");
            ids = new Integer[st.length];
            for (int i = 0; i < st.length; i++) {
                ids[i] = Integer.valueOf(st[i]);
            }
        }
        roleService.grantMenu(id, Arrays.asList(ids));
        return "redirect:/hjpt/role/list";
    }

     /* @GetMapping(value = "/{id}/select-resource")
    public String selectRole(@PathVariable("id") String id, Model model) {
        model.addAttribute("list", roleService.selectResources(id));
        model.addAttribute("api", "/role/" + id + "/grant-resource");
        return "admin/role/grant-resource";
    }

    *//**
     * 为角色分配资源
     *
     * @param id
     * @param rid
     * @return
     *//*
    @PostMapping(value = "/{id}/grant-resource")
    @LogAopAnnotation
    public String grantResources(@PathVariable("id") String id, String[] rid) {
        if (rid == null) {
            rid = new String[0];
        }
        roleService.grantResource(id, Arrays.asList(rid));
        return "redirect:/role";
    }
*/
}
