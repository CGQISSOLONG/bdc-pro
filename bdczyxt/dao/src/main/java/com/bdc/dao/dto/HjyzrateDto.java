package com.bdc.dao.dto;
import lombok.Data;

/**
 * @author 创建人：laill
 * @version 版本号：V1.0
 * <p>
 * **************************修订记录***************************************
 * <p>
 * 2019年11月18日 laill 创建该类功能。
 * <p>
 * ***********************************************************************
 * </p>
 * @ClassName 类名：HjyzrateDto
 * @Description 功能说明：汇交一致率
 * <p>
 * TODO
 * </p>
 * ***********************************************************************
 * @date 创建日期：2019年11月18日
 */
@Data
public class HjyzrateDto extends HjfxDto {
    private Long jrCount;

    private Long dbCount;

    private Long tjLogCount;

    private Long yjLogCount;

    private Long zsJrl;

    private Long zsDtdbjrl;

    private Long zsDbl;
}
