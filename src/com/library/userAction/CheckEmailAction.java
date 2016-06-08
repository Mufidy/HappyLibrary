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
	        // ����debug����  
	        props.setProperty("mail.debug", "true");  
	        // ���ͷ�������Ҫ�����֤  
	        props.setProperty("mail.smtp.auth", "true");  
	        // �����ʼ�������������  
	        props.setProperty("mail.host", "smtp.163.com");  
	        // �����ʼ�Э������  
	        props.setProperty("mail.transport.protocol", "smtp");  
	          
	        // ���û�����Ϣ  
	        Session session = Session.getInstance(props);  
	          
	        // �����ʼ�����  
	        Message msg = new MimeMessage(session);  
	        msg.setSubject("����ͼ��ݽ�����������");  
	        // �����ʼ�����  
	        String mailString = user.getUsername()+"��ã�\n���ʼ�Ϊ�����ʼ����뽫�������ַ��������������򽫲��ܽ��յ������ʵʱ���ѡ������ʼ�Ϊϵͳ�Զ����ͣ�����ֱ�ӻظ�����";
	        msg.setText(mailString);  
	        // ���÷�����  
	        msg.setFrom(new InternetAddress("library0110@163.com"));  
	        
	        Transport transport = session.getTransport();  
	        // �����ʼ�������  
	        transport.connect("library0110", "0110Library");  
	        // �����ʼ�  
	        transport.sendMessage(msg, new Address[] {new InternetAddress(user.getEmail())});  
	        // �ر�����  
	        transport.close();          
		}
		
		return "success";
	}

}
