package com.admin.controller.admin.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName 类名：
 * @Description 功能说明：
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelectResourceVo {

    private String rid;

    private String label;

    private boolean checked;

}
