
package com.bdc.entity.primary;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Transient;
import java.util.Date;

/**
 * 字典Entity
 */
@Data
@NoArgsConstructor
public class Dict {
	private Integer id;
	/**
	 * 数据值
	 */
	private String value;
	/**
	 * 标签名
	 */
	private String label;
	/**
	 * 类型
	 */
	private String type;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 排序
	 */
	private Integer sort;

	/**
	 * 父Id
	 */
	private String parentId;
	/**
	 * 备注
	 */
	private String remarks;
	/**
	 * 创建者
	 */
	private Long createBy;
	private String createDate;
	private Long updateBy;
	private String updateDate;
	private String delFlag;

	@Transient
	private String check;

	public Dict(String type){
		this.type = type;
	}

	@Override
	public String toString() {
		return label;
	}
}