package com.ws.admin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * 登陆用的验证码类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CaptchaImg implements Serializable {

    private static final long serialVersionUID = -6036586768080384600L;
    private String username;
    private String captchaCode;
    private long createTime;
}
