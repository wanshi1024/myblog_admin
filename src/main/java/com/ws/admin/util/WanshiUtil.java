package com.ws.admin.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;

public class WanshiUtil {

    /**
     * 发送验证码邮件
     *
     * @param to
     * @param content
     * @throws Exception
     */
    public static void sendEmail(String to, String content) throws Exception {
        String myEmail = "2942504702@qq.com";
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", "smtp.qq.com");
        props.put("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.port", "");
        props.put("mail.smtp.ssl.enable", "true");
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myEmail, "auuedhlpaojedhec");
            }
        });
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(myEmail));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
//        message.setRecipient(Message.RecipientType.CC, new InternetAddress(myEmail));
        message.setSubject("主题");
        message.setContent(content, "text/html;charset=utf-8");
        Transport.send(message);
        System.out.println("发送成功");
    }

    /**
     * 随机产生字符串
     * @param length 字符串长度
     * @param type 类型 (0: 仅数字; 1:仅字符; 2:数字和字符)
     * @return
     */
    public static String createCaptcha(int length, int type) {
        String str = "";
        int beginChar = 'a';
        int endChar = 'z';
        // 只有数字
        if (type == 0) {
            beginChar = 'z' + 1;
            endChar = 'z' + 10;
        }
        // 只有小写字母
        else if (type == 1) {
            beginChar = 'a';
            endChar = 'z';
        }
        // 有数字和字母
        else if (type == 2){
            beginChar = 'a';
            endChar = 'z' + 10;
        }

        // 生成随机类
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int tmp = (beginChar + random.nextInt(endChar - beginChar));
            // 大于'z'的是数字
            if (tmp > 'z') {
                tmp = '0' + (tmp - 'z');
            }
            str += (char) tmp;
        }
        return str;
    }

}
