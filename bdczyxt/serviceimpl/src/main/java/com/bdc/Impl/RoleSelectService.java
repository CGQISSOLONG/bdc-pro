package com.bdc.Impl;



import com.bdc.dao.vo.SelectRoleVo;
import com.bdc.entity.Role;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;


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
@Service
public class RoleSelectService {

    /***
     * 用户具备的角色与所有角色合并，合并结果是所有的角色中
     * 用户具备的角色checked=true
     *
     * @param all
     * @param part
     * @return
     */
    public static List<SelectRoleVo> mergeRole(List<Role> all, List<Role> part) {
        if (CollectionUtils.isEmpty(all)) {
            return null;
        }

        if (CollectionUtils.isEmpty(part)) {
            return all.stream().map(role -> new SelectRoleVo(role.getId(), role.getRoleName(),
                    false,role.getDescription()))
                    .collect(Collectors.toList());
        }

        return all.stream().map(role -> {
           /* if (part.contains(role)) {
                return new SelectRoleVo(role.getId(), role.getRoleName(), true,role.getDescription());
            }*/
            for(Role role1:part){
                if(role1.getId().equals(role.getId())){
                    return new SelectRoleVo(role.getId(), role.getRoleName(), true,role.getDescription());
                }
            }
            return new SelectRoleVo(role.getId(), role.getRoleName(), false,role.getDescription());
        }).collect(Collectors.toList());
    }
}
