package com.ws.admin.mail;

import com.ws.admin.util.WanshiUtil;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Test {



    @org.junit.jupiter.api.Test
    public void test2() throws Exception {
        WanshiUtil.sendEmail("2672575663@qq.com","111");
    }


}
