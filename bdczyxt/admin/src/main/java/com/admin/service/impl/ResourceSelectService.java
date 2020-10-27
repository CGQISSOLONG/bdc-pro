package com.admin.service.impl;


import com.admin.controller.admin.vo.SelectMenuVo;
import com.admin.controller.admin.vo.SelectResourceVo;
import com.admin.entity.primary.AdminMenu;
import com.admin.entity.primary.AdminResource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResourceSelectService {

    /***
     * 角色具备的资源与所有资源合并，合并结果是所有的资源中
     * 角色具备的资源checked=true
     *
     * @param all
     * @param part
     * @return
     */
    public List<SelectResourceVo> mergeResource(List<AdminResource> all, List<AdminResource> part) {
        if (CollectionUtils.isEmpty(all)) {
            return null;
        }

        if (CollectionUtils.isEmpty(part)) {
            return all.stream().map(role -> new SelectResourceVo(role.getId(), role.getTitle(), false))
                    .collect(Collectors.toList());
        }

        return all.stream().map(role -> {
            if (part.contains(role)) {
                return new SelectResourceVo(role.getId(), role.getTitle(), true);
            }
            return new SelectResourceVo(role.getId(), role.getTitle(), false);
        }).collect(Collectors.toList());
    }


    /***
     * 角色具备的菜单资源与所有菜单合并，合并结果是所有的菜单中
     * 角色具备的菜单checked=true
     *
     * @param all
     * @param part
     * @return
     */
    public List<SelectMenuVo> mergeMenus(List<AdminMenu> all, List<AdminMenu> part) {
        if (CollectionUtils.isEmpty(all)) {
            return null;
        }

        if (CollectionUtils.isEmpty(part)) {
            return all.stream().map(role -> new SelectMenuVo(role.getId(), role.getLabel(), false))
                    .collect(Collectors.toList());
        }

        return all.stream().map(role -> {
            if (part.contains(role)) {
                return new SelectMenuVo(role.getId(), role.getLabel(), true);
            }
            return new SelectMenuVo(role.getId(), role.getLabel(), false);
        }).collect(Collectors.toList());
    }
}
