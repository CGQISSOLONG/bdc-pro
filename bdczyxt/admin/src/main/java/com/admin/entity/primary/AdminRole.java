package com.admin.entity.primary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName 类名：AdminRole
 * @Description 功能说明：后台角色
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class AdminRole implements Serializable {

	private static final long serialVersionUID = -999108187463063344L;

	/**
	 * 主键
	 */
	private Integer id;

	/**
	 * 角色名称
	 */
	private String roleName;

	/**
	 * 描述
	 */
	private String description;

	/**
	 * 创建时间
	 */
	private String createTime;

	/**
	 * 更新时间
	 */
	private String updateTime;

	/**
	 * 状态0：删除，1：启用，2：禁用
	 */
	private Integer status;

	public AdminRole(Integer id, String roleName, String desc){
		this.roleName=roleName;
		this.description=desc;
		this.id=id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		AdminRole role = (AdminRole) o;
		return id != null ? id.equals(role.id) : role.id == null;

	}
	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}
}

