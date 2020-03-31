package com.ws.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ws.admin.entity.Captcha;
import com.ws.admin.entity.CaptchaImg;
import com.ws.admin.entity.User;
import com.ws.admin.mapper.UserMapper;
import com.ws.admin.util.CaptchaImgUtil;
import com.ws.admin.util.JwtHelper;
import com.ws.admin.util.WanshiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserMapper userMapper;
    Map<String, Object> map = new HashMap<>();

    /**
     * 注册
     *
     * @param user
     * @param captcha
     * @param request
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Object register(User user,
                           @RequestParam("captcha") String captcha,
                           HttpServletRequest request) {
        System.out.println(user + "---" + captcha);

        HttpSession session = request.getSession();
        Captcha c = (Captcha) session.getAttribute(user.getEmail());
        System.out.println(c);
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

    /**
     * 判断邮箱是否存在
     *
     * @param email
     * @return
     */
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

    /**
     * 验证用户名是否存在
     *
     * @param username
     * @return
     */
    @RequestMapping(value = "/usernameIsExist", method = RequestMethod.POST)
    public Object usernameIsExist(@RequestParam("username") String username) {
//        System.out.println(username);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        Integer count = userMapper.selectCount(queryWrapper);
        if (count >= 1) {
            map.put("code", 1);
            map.put("message", "用户名已存在");
        } else {
            map.put("code", 0);
            map.put("message", "用户名不存在");
        }
        return map;
    }

    /**
     * 注册验证码
     *
     * @param email
     * @param request
     * @return
     */
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
        System.out.println((Captcha)session.getAttribute(email));
        map.put("code", 1);
        map.put("message", "验证码已发送,请查看您的邮箱");
        return map;
    }

    /**
     * 登陆验证码图片 base64
     *
     * @param username
     * @return
     */
    @RequestMapping(value = "/getCaptchaImg", method = RequestMethod.POST)
    public Object getCaptchaImg(@RequestParam("username") String username,
                                HttpServletRequest request) {
        HttpSession session = request.getSession();
        Map<String, String> captchaImgBase64 = CaptchaImgUtil.getCaptchaImgBase64(4);
        CaptchaImg captchaImg = new CaptchaImg(username, captchaImgBase64.get("captchaCode"), System.currentTimeMillis());
        session.setAttribute(username, captchaImg);
        return captchaImgBase64;
    }

    @Autowired
    private JwtHelper jwtHelper;

    /**
     * 登陆
     *
     * @param user
     * @param captcha
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object login(User user,
                        @RequestParam("captcha") String captcha,
                        HttpServletRequest request) {

        if (user.getUsername() == null || user.getPassword() == null || captcha == null) {
            map.put("code", 0);
            map.put("message", "登陆错误,请正确填写信息");
            return map;
        }
        HttpSession session = request.getSession();
        CaptchaImg captchaImg = (CaptchaImg) session.getAttribute(user.getUsername());

        //判断验证码正确
        if (captchaImg == null || !captchaImg.getCaptchaCode().equals(captcha)) {
            map.put("code", 0);
            map.put("message", "验证码错误");
            return map;
        }

        //判断验证码超时
        if (System.currentTimeMillis() - captchaImg.getCreateTime() > 2 * 60 * 1000) {
            map.put("code", 0);
            map.put("message", "验证码超时,请重新获取");
            return map;
        }

        session.removeAttribute(user.getUsername());

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername())
                .eq("password", user.getPassword());
        User userResult = userMapper.selectOne(queryWrapper);
        if (userResult != null) {
            map.put("code", 1);
            map.put("message", "用户名密码正确,登陆成功");
            map.put("userInfo", userResult);
            map.put("token", jwtHelper.createToken(userResult.getId()));
        } else {
            map.put("code", 0);
            map.put("message", "用户名或密码错误,请核对后登陆");
        }

        return map;
    }

    /**
     * 验证token
     *
     * @param token
     * @return
     */
    @RequestMapping(value = "/checkToken", method = RequestMethod.POST)
    public Object checkToken(@RequestParam("token") String token) {

//        System.out.println(token);
        Integer integer = jwtHelper.verifyTokenAndGetUserId(token);
        if (integer == 0) {
            map.put("code", 0);
            map.put("message", "token无效,请登录");
        } else {
            User user = userMapper.selectById(integer);
            if (user == null) {
                map.put("code", 0);
                map.put("message", "自动登录失败");
            } else {
                map.put("code", 1);
                map.put("userInfo", user);
            }
        }
        return map;
    }

    /**
     * 修改头像 把头像的url修改进来
     * @param user
     * @return
     */
    @RequestMapping(value = "/uploadAvatar", method = RequestMethod.POST)
    public Object uploadAvatar(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", user.getId());
        int update = userMapper.update(user, queryWrapper);
        if (update > 0) {
            map.put("code", 1);
            map.put("message", "头像上传成功");
            map.put("userInfo",user);
        } else {
            map.put("code", 0);
            map.put("message", "头像上传失败,请稍后重试或咨询站长");
        }
        return map;
    }
}
