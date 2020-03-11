package com.ws.admin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 注册用的验证码类
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Captcha {
    private String code;
    private String email;
    private long createTime;
}
