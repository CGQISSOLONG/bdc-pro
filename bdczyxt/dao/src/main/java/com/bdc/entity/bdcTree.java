package com.bdc.entity;

import lombok.Data;

@Data
public class bdcTree {
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
