package com.bdc.dao.dto;


import com.bdc.entity.Menu;
import lombok.Data;

/**
 * @author 创建人：laill
 * @version 版本号：V1.0
 * <p>
 * **************************修订记录***************************************
 * <p>
 * 2019年06月13日 laill 创建该类功能。
 * <p>
 * ***********************************************************************
 * </p>
 * @ClassName 类名：HjptTreeDto
 * @Description 功能说明：
 * <p>
 * TODO
 * </p>
 * ***********************************************************************
 * @date 创建日期：2019年06月13日
 */
@Data
public class HjptTreeDto {
    private Integer id;
    /**
     * 链接
     */
    private String url;

    private String label;

    /**
     * 父节点的路径与父节点的id路径，用","分开，0表示父节点是根节点
     */
    private String parentIds = "0";

    private Integer rank;

    private Integer parentId;

    private String imageUrl;

    /**
     * 排序
     */
    private Integer orderNum = 1;

    /**
     * 扩展字段。菜单类型，供不同业务区分
     */
    private Integer type;

    /**
     * 样式，方便ui展现
     */
    private String style;

    /**
     * 状态 是否禁用
     */
    private String disabled;


    private Menu parent;
}
