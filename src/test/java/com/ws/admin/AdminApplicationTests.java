package com.ws.admin;

import com.ws.admin.entity.User;
import com.ws.admin.mapper.ArticleLabelMapper;
import com.ws.admin.mapper.ArticleMapper;
import com.ws.admin.mapper.UserMapper;
import com.ws.admin.util.JwtHelper;
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

    @Resource
    private ArticleLabelMapper articleLabelMapper;
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

    @Test
    void test2(){
        Integer integer = articleLabelMapper.selectCount(null);
        System.out.println(integer);
    }

    @Resource
    ArticleMapper articleMapper;
    @Test
    void test3(){
       articleMapper.increaseReadCount(6);
    }

    @Test
    void test5(){

    }

}
