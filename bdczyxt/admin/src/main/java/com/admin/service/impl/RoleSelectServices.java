package com.admin.service.impl;


import com.admin.controller.admin.vo.SelectRoleVo;
import com.admin.entity.primary.AdminRole;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleSelectServices {

    /***
     * 用户具备的角色与所有角色合并，合并结果是所有的角色中
     * 用户具备的角色checked=true
     *
     * @param all
     * @param part
     * @return
     */
    public List<SelectRoleVo> mergeRole(List<AdminRole> all, List<AdminRole> part) {
        if (CollectionUtils.isEmpty(all)) {
            return null;
        }

        if (CollectionUtils.isEmpty(part)) {
            return all.stream().map(role -> new SelectRoleVo(role.getId(), role.getRoleName(),
                    false,role.getDescription()))
                    .collect(Collectors.toList());
        }

        return all.stream().map(role -> {
            if (part.contains(role)) {
                return new SelectRoleVo(role.getId(), role.getRoleName(), true,role.getDescription());
            }
            return new SelectRoleVo(role.getId(), role.getRoleName(), false,role.getDescription());
        }).collect(Collectors.toList());
    }
}
