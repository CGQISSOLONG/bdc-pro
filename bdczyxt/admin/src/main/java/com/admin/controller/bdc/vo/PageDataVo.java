package com.admin.controller.bdc.vo;

import lombok.Data;

import java.util.List;

@Data
public class PageDataVo<T> {
    private List<T> data;
    private Long recordsFiltered;
    private Long recordsTotal;
}
