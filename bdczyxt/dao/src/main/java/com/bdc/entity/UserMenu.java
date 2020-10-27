package com.bdc.entity;
import lombok.Data;

import java.util.Date;

@Data
public class UserMenu {

    private Integer id;
    private Integer userId;//用户标识码
    private Integer menuId;//菜单标识码
    private Date createTime;//创建时间

    public UserMenu(Date createTime){
        this.createTime = createTime;
    }
}
