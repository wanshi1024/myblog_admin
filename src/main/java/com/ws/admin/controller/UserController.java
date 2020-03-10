package com.ws.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ws.admin.entity.Captcha;
import com.ws.admin.entity.User;
import com.ws.admin.mapper.UserMapper;
import com.ws.admin.util.WanshiUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserMapper userMapper;
    Map<String, Object> map = new HashMap<>();

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Object register(User user,
                           @RequestParam("captcha") String captcha,
                           HttpServletRequest request) {
        System.out.println(user + "---" + captcha);

        HttpSession session = request.getSession();
        Captcha c = (Captcha) session.getAttribute(user.getEmail());
        if (c == null) {
            map.put("code", 0);
            map.put("message", "验证码无效");
            return map;
        }

        if (System.currentTimeMillis() - c.getCreateTime() > 2 * 60 * 1000) {
            map.put("code", 0);
            map.put("message", "验证码已失效");
            session.removeAttribute(user.getEmail());
            return map;
        }

        if (c.getCode().equals(captcha)) {
            userMapper.insert(user);
            map.put("code", 1);
            map.put("message", "注册成功");
        } else {
            map.put("code", 0);
            map.put("message", "验证码错误,请稍后重试");
        }

        return map;
    }

    @RequestMapping(value = "/emailIsExist", method = RequestMethod.POST)
    public Object emailIsExist(@RequestParam("email") String email) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        Integer count = userMapper.selectCount(queryWrapper);
        if (count >= 1) {
            map.put("code", 0);
            map.put("message", "此邮箱已存在");
        } else {
            map.put("code", 1);
            map.put("message", "邮箱不存在,可以注册");
        }
        return map;
    }

    @RequestMapping(value = "/getCaptcha", method = RequestMethod.POST)
    public Object getCaptcha(@RequestParam("email") String email,
                             HttpServletRequest request) {

        String captchaStr = WanshiUtil.createCaptcha(6, 2);
        System.out.println(captchaStr);
        try {
            WanshiUtil.sendEmail(email, "您的验证码是:" + captchaStr + ",有效时间2分钟");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 0);
            map.put("message", "验证码获取失败,请稍后重新获取");
            return map;
        }
        HttpSession session = request.getSession();
        Captcha captcha = new Captcha(captchaStr, email, System.currentTimeMillis());
        session.setAttribute(email, captcha);
        map.put("code", 1);
        map.put("message", "验证码已发送,请查看您的邮箱");
        return map;
    }

}
