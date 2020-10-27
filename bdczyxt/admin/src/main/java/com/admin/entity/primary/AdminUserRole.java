package com.admin.entity.primary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * @author 创建人：laill
 * @version 版本号：V1.0
 * <p>
 * **************************修订记录***************************************
 * <p>
 * 2019年06月17日 laill 创建该类功能。
 * <p>
 * ***********************************************************************
 * </p>
 * @ClassName 类名：AdminUserRole
 * @Description 功能说明：后台用户角色
 * <p>
 * TODO
 * </p>
 * ***********************************************************************
 * @date 创建日期：2019年06月17日
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminUserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 角色id
     */
    private Integer roleId;
}
