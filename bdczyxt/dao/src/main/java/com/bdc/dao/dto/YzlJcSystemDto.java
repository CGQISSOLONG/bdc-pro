package com.bdc.dao.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author 创建人：laill
 * @version 版本号：V1.0
 * <p>
 * **************************修订记录***************************************
 * <p>
 * 2019年11月22日 laill 创建该类功能。
 * <p>
 * ***********************************************************************
 * </p>
 * @ClassName 类名：YzlJcSystemDto
 * @Description 功能说明：
 * <p>
 * TODO
 * </p>
 * ***********************************************************************
 * @date 创建日期：2019年11月22日
 */
@Data
public class YzlJcSystemDto {
    private String id;
    @JsonFormat( pattern = "yyyy-MM-dd")
    private Date compareDate;
    private String statistics;
    private String pdfJrCount;
    private String pdfJrDb;
    private String pdfDbCount;
    private String jcDbJrcount;
    private String jcDbLogcount;
    private String zsJrCount;
    private String zsJrDbcount;
    private String zsDbCount;
    private String qxdm;
}
