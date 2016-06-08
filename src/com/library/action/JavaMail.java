package com.library.action;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMail {
	public void sendEmail(String borrowUsername, String bookname,String userEmail) throws MessagingException{
		 Properties props = new Properties();  
        // 开启debug调试  
        props.setProperty("mail.debug", "true");  
        // 发送服务器需要身份验证  
        props.setProperty("mail.smtp.auth", "true");  
        // 设置邮件服务器主机名  
        props.setProperty("mail.host", "smtp.163.com");  
        // 发送邮件协议名称  
        props.setProperty("mail.transport.protocol", "smtp");  
          
        // 设置环境信息  
        Session session = Session.getInstance(props);  
          
        // 创建邮件对象  
        Message msg = new MimeMessage(session);  
        msg.setSubject("虚拟图书馆借书申请提醒");  
        // 设置邮件内容  
        String mailString = "亲爱的同学你好：\n"+borrowUsername+"同学 申请向您借阅  《"+bookname+"》，记得及时上网查看哦~（本邮件为系统自动发送，请勿直接回复！）";
        msg.setText(mailString);  
        // 设置发件人  
        msg.setFrom(new InternetAddress("library0110@163.com"));  
        
        Transport transport = session.getTransport();  
        // 连接邮件服务器  
        transport.connect("library0110", "0110Library");  
        // 发送邮件  
        transport.sendMessage(msg, new Address[] {new InternetAddress(userEmail)});  
        // 关闭连接  
        transport.close();  

	}
}
