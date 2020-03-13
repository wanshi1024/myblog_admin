package com.ws.admin;

import com.ws.admin.demo.JwtHelper;
import com.ws.admin.entity.User;
import com.ws.admin.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@MapperScan(value = "com.ws.admin.mapper")
class AdminApplicationTests {

    @Resource
    private UserMapper userMapper;
    @Test
    void test1() {
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }

    public static void main(String[] args) {
        JwtHelper jwtHelper = new JwtHelper();
        String token = jwtHelper.createToken(6);
        System.out.println(token);

        Integer integer = jwtHelper.verifyTokenAndGetUserId(token);
        System.out.println(integer);
    }
    void test2(){

    }

}
