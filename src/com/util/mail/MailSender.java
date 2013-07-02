package com.util.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * �ʼ�������
 * @author Xinya Ye
 */
public class MailSender {
///**
// * ���ı���ʽ�����ʼ�
// * @param mailInfo �������ʼ���Ϣ
// */
	public boolean sendTextMail(MailSendInfo mailInfo){
	MyAuthenticator authenticator = null;
	Properties p = mailInfo.getProperties();
	if(mailInfo.isValidate()){
		authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassWord());
		
	}
	
	//�����ʼ��Ự���Ժ�������֤������һ�������ʼ���session
	Session sendMailSession = Session.getDefaultInstance(p, authenticator);
	try{
		Message msg = new MimeMessage(sendMailSession);
		Address fromAddress = new InternetAddress(mailInfo.getFromAddress());
		msg.setFrom(fromAddress);
		Address toAddress = new InternetAddress(mailInfo.getToAddress());
		//�ռ���
		msg.setRecipient(Message.RecipientType.TO, toAddress);
	    //����
		msg.setSubject(mailInfo.getSubject());
		msg.setSentDate(new Date());
		if(mailInfo.getAttachFile() != null){
	        InternetAddress[] cc = InternetAddress.parse(mailInfo.getAttachFile());
	        msg.setRecipients(Message.RecipientType.CC, cc);
	       }
		String mailContent = mailInfo.getContent();
		msg.setText(mailContent);
		// send mail
		Transport.send(msg);
		return true;
	}catch(MessagingException ex){
		ex.printStackTrace();
	}
	
	return false;
	
	}
	
	/**
	 * ��html ��ʽ����
	 * @param mailInfo
	 */
	public static boolean sendHtmlMail(MailSendInfo mailInfo){
		//�ж������֤
		MyAuthenticator authenticator = null;
		Properties p = mailInfo.getProperties();
		if(mailInfo.isValidate()){
			authenticator = new MyAuthenticator(mailInfo.getUserName(),mailInfo.getPassWord());
			
		}
		// �����ʼ��Ự���Ժ�������֤������һ�������ʼ���session
		Session sendMailSession = Session.getDefaultInstance(p, authenticator);
		try{
			Message msg = new MimeMessage(sendMailSession);
			Address fromAddress = new InternetAddress(mailInfo.getFromAddress());
			msg.setFrom(fromAddress);
			Address toAddress = new InternetAddress(mailInfo.getToAddress());
			//�ռ���
			msg.setRecipient(Message.RecipientType.TO, toAddress);
		    //����
			msg.setSubject(mailInfo.getSubject());
			msg.setSentDate(new Date());
			// MiniMultipart����һ�������࣬����MimeBodyPart���͵Ķ��� 
			Multipart mainPart = new MimeMultipart();
			// ����һ������HTML���ݵ�MimeBodyPart
			BodyPart html = new MimeBodyPart();
			// ����HTML����
			html.setContent(mailInfo.getContent(),"text/html; charset = utf-8");
			mainPart.addBodyPart(html);
			// ��MiniMultipart��������Ϊ�ʼ�����
			msg.setContent(mainPart);
			
			// send mail
			Transport.send(msg);
			return true;
		}catch(MessagingException ex){
			ex.printStackTrace();
		}
		
		return false;
		
	}
	
//	public boolean sendMail(MailSendInfo mailInfo){
//		MyAuthenticator authenticator = null;
//		Properties p = mailInfo.getProperties();
//		if(mailInfo.isValidate()){
//			authenticator = new MyAuthenticator(mailInfo.getUserName(),mailInfo.getPassWord());
//			
//		}
//		Session sendMailSession = Session.getDefaultInstance(p, authenticator);
//		   try {
//		    Message msg = new MimeMessage(sendMailSession);
//		    Address fromAddress = new InternetAddress(mailInfo.getFromAddress());
//		    msg.setFrom(fromAddress);
//		    Address toAddress = new InternetAddress(mailInfo.getToAddress());
//		    //InternetAddress[] toAddress =  InternetAddress.parse(mailInfo.getToAddress());
//		    msg.setRecipient(Message.RecipientType.TO, toAddress);
//		    
//		    if(mailInfo.getAttachFile() != null){
//		        InternetAddress[] cc = InternetAddress.parse(mailInfo.getAttachFile());
//		        msg.setRecipients(Message.RecipientType.CC, cc);
//		       }
//		   // msg.setRecipients(Message.RecipientType.CC, attachFile);
//		   
//		    msg.setSubject(mailInfo.getSubject());
//		    Multipart mainPart =  new MimeMultipart();
//		    BodyPart message = new MimeBodyPart();
//		    message.setContent(mailInfo.getContent(),"text/html; charset = utf-8");
//		    msg.setContent(mainPart);
//		    msg.setSentDate(new Date());
//		    Transport.send(msg);
//		    return true;
//		   } catch  (MessagingException e) {
//		    e.printStackTrace();
//		   }
//		   return false;
//		}
}
