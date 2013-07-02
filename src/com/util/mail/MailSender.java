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
 * 邮件发送器
 * @author Xinya Ye
 */
public class MailSender {
///**
// * 以文本格式发送邮件
// * @param mailInfo 待发送邮件信息
// */
	public boolean sendTextMail(MailSendInfo mailInfo){
	MyAuthenticator authenticator = null;
	Properties p = mailInfo.getProperties();
	if(mailInfo.isValidate()){
		authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassWord());
		
	}
	
	//根据邮件会话属性和密码验证器构造一个发送邮件的session
	Session sendMailSession = Session.getDefaultInstance(p, authenticator);
	try{
		Message msg = new MimeMessage(sendMailSession);
		Address fromAddress = new InternetAddress(mailInfo.getFromAddress());
		msg.setFrom(fromAddress);
		Address toAddress = new InternetAddress(mailInfo.getToAddress());
		//收件人
		msg.setRecipient(Message.RecipientType.TO, toAddress);
	    //主题
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
	 * 以html 格式发送
	 * @param mailInfo
	 */
	public static boolean sendHtmlMail(MailSendInfo mailInfo){
		//判断身份验证
		MyAuthenticator authenticator = null;
		Properties p = mailInfo.getProperties();
		if(mailInfo.isValidate()){
			authenticator = new MyAuthenticator(mailInfo.getUserName(),mailInfo.getPassWord());
			
		}
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session sendMailSession = Session.getDefaultInstance(p, authenticator);
		try{
			Message msg = new MimeMessage(sendMailSession);
			Address fromAddress = new InternetAddress(mailInfo.getFromAddress());
			msg.setFrom(fromAddress);
			Address toAddress = new InternetAddress(mailInfo.getToAddress());
			//收件人
			msg.setRecipient(Message.RecipientType.TO, toAddress);
		    //主题
			msg.setSubject(mailInfo.getSubject());
			msg.setSentDate(new Date());
			// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象 
			Multipart mainPart = new MimeMultipart();
			// 创建一个包含HTML内容的MimeBodyPart
			BodyPart html = new MimeBodyPart();
			// 设置HTML内容
			html.setContent(mailInfo.getContent(),"text/html; charset = utf-8");
			mainPart.addBodyPart(html);
			// 将MiniMultipart对象设置为邮件内容
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
