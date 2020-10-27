package com.admin.controller.admin;



import com.admin.entity.primary.AdminResource;
import com.admin.service.impl.AdminResourceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
 * @author 创建人：laill
 * @version 版本号：V1.0
 * <p>
 * **************************          修订记录***************************************
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
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    protected AdminResourceService resourceService;

    @RequestMapping(method = RequestMethod.POST,value = "/add")
    public String create(AdminResource resource){
        resourceService.create(resource);
        return "redirect:/resource";
    }

    @RequestMapping(value = "/{id}/modify", method = RequestMethod.POST)
    public String modify(@PathVariable("id") String id, AdminResource resource) {
        resource.setId(id);
        resourceService.modify(resource);
        return "redirect:/resource";
    }

    @RequestMapping(value = "/{id}/status",method = RequestMethod.PUT)
    @ResponseBody
    public void switchStatus(@PathVariable("id") String id, @RequestParam("disable") boolean disable){
        resourceService.switchStatus(id,disable);
    }

    @RequestMapping(value = "/{id}/delete",method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable("id")String id){
         resourceService.delete(id);
    }

    @RequestMapping(value = "/form",method = RequestMethod.GET)
    public String toform(@RequestParam(name = "id",required = false)String id, Model model){
        String api="/resource/add";
        if(StringUtils.isNotBlank(id)){
            model.addAttribute("resource",resourceService.get(id));
            api="/resource/"+id+"/modify";
          }
          model.addAttribute("api",api);

        return "resource/form";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("list",resourceService.list());
        return "resource/list";
        }


}
