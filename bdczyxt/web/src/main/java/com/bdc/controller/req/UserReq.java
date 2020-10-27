package com.bdc.controller.req;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UserReq {

    private String account;

    private String password;

    private String validateCode;

    private String browserName;

    private String browserVersion;
}
