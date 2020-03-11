package com.ws.admin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 登陆用的验证码类
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CaptchaImg {

    private String username;
    private String captchaCode;
    private long createTime;
}
