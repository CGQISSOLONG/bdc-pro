package com.bdc.dao.dto;

import lombok.Data;

/**
 * @ClassName 类名：AverageTimeDto
 * @Description 功能说明：平均用时dto
 */
@Data
public class AverageTimeDto extends HjfxDto {
    private Long bdcdjCount;

    private Double totalTime;

    private Double zsCount;
}
