package com.library.userAction;

import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.library.hibernate.Actions;
import com.library.material.User;
import com.opensymphony.xwork2.Action;

public class CheckEmailAction implements Action {

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		Actions actions = new Actions();
		List<User> allUsers = actions.getAllUsers(0);
		for(int i=0;i<allUsers.size();i++){
			User user = allUsers.get(i);
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
	        String mailString = user.getUsername()+"你好：\n本邮件为测试邮件，请将该邮箱地址加入白名单，否则将不能接收到借书的实时提醒。（本邮件为系统自动发送，请勿直接回复！）";
	        msg.setText(mailString);  
	        // 设置发件人  
	        msg.setFrom(new InternetAddress("library0110@163.com"));  
	        
	        Transport transport = session.getTransport();  
	        // 连接邮件服务器  
	        transport.connect("library0110", "0110Library");  
	        // 发送邮件  
	        transport.sendMessage(msg, new Address[] {new InternetAddress(user.getEmail())});  
	        // 关闭连接  
	        transport.close();          
		}
		
		return "success";
	}

}
