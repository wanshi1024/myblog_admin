package com.ws.admin.mail;

import com.ws.admin.util.WanshiUtil;

public class Test {
    public static void main(String[] args) {
        try {
            WanshiUtil.sendEmail("2672575663@qq.com","<mark>验证码:123456</mark>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
