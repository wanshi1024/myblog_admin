package com.ws.admin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * 注册用的验证码类
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Captcha implements Serializable {
    private static final long serialVersionUID = 8074579393463339948L;
    private String code;
    private String email;
    private long createTime;
}
