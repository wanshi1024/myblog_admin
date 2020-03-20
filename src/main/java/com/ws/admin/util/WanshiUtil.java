package com.ws.admin.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
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
     *
     * @param length 字符串长度
     * @param type   类型 (0: 仅数字; 1:仅字符; 2:数字和字符)
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
        else if (type == 2) {
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

    /**
     * 备份数据库
     * @param hostIP ip地址，可以是本机也可以是远程
     * @param userName 数据库的用户名
     * @param password 数据库的密码
     * @param savePath 备份的路径
     * @param fileName 备份的文件名
     * @param databaseName 需要备份的数据库的名称
     * @return
     */
    public static boolean backup(String hostIP, String userName, String password, String savePath, String fileName,
                                 String databaseName) {
        fileName +=".sql";
        File saveFile = new File(savePath);
        if (!saveFile.exists()) {// 如果目录不存在
            saveFile.mkdirs();// 创建文件夹
        }
        if (!savePath.endsWith(File.separator)) {
            savePath = savePath + File.separator;
        }

        //拼接命令行的命令
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("mysqldump").append(" --opt").append(" -h").append(hostIP);
        stringBuilder.append(" --user=").append(userName).append(" --password=").append(password)
                .append(" --lock-all-tables=true");
        stringBuilder.append(" --result-file=").append(savePath + fileName).append(" --default-character-set=utf8 ")
                .append(databaseName);
        try {
            //调用外部执行exe文件的javaAPI
            Process process = Runtime.getRuntime().exec(stringBuilder.toString());
            if (process.waitFor() == 0) {// 0 表示线程正常终止。
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        boolean b = WanshiUtil.backup("localhost", "root", "root", "./sql", "myblog", "blog");

    }

}
