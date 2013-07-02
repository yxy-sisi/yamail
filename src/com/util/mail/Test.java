package com.util.mail;

public class Test {
	public static void main(String[] args){
		MailSendInfo mailInfo = new MailSendInfo();
		mailInfo.setMailServerHost("smtp.gmail.com");
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);
		mailInfo.setUserName("ye.cassie@gmail.com");
		mailInfo.setPassWord("just@4you");
		mailInfo.setFromAddress("ye.cassie@gmail.com");
		mailInfo.setToAddress("ye.cassie@gmail.com");
		mailInfo.setSubject("subject is my first test for java mail");
		mailInfo.setContent("This is just a test for beginer developer");
		
		MailSender sms = new MailSender();
		sms.sendHtmlMail(mailInfo);
		sms.sendHtmlMail(mailInfo);
	}



}
