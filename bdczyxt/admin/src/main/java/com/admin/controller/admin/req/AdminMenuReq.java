package com.admin.controller.admin.req;


import com.bdc.common.Constants;
import lombok.Data;

/**
 * @ClassName 类名：AdminMenuReq
 * @Description 功能说明：管理系统菜单
 */
@Data
public class AdminMenuReq {
    private String label;

    /**
     * 父节点的路径与父节点的id路径，用","分开，0表示父节点是根节点
     */
    private String parentIds= Constants.NO;

    /**
     * 排序
     */
    private Integer orderNum=1;


    private String url;

    /**
     * 扩展字段。菜单类型，供不同业务区分
     */
    private Integer type = 1;

    /**
     * 样式，方便ui展现
     */
    private String style;

    /**
     * 新建菜单默认禁用
     */
    private String disabled= Constants.YES;

    /**
     * 新增菜单级数
     */
    private Integer rank=1;

    /**
     * 父id
     */
    private Integer parentId;
}
