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
        String mailString = "�װ���ͬѧ��ã�\n"+borrowUsername+"ͬѧ ������������  ��"+bookname+"�����ǵü�ʱ�����鿴Ŷ~�����ʼ�Ϊϵͳ�Զ����ͣ�����ֱ�ӻظ�����";
        msg.setText(mailString);  
        // ���÷�����  
        msg.setFrom(new InternetAddress("library0110@163.com"));  
        
        Transport transport = session.getTransport();  
        // �����ʼ�������  
        transport.connect("library0110", "0110Library");  
        // �����ʼ�  
        transport.sendMessage(msg, new Address[] {new InternetAddress(userEmail)});  
        // �ر�����  
        transport.close();  

	}
}
