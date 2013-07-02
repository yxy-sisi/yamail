package com.util.mail;

import java.util.Properties;


public class MailSendInfo{
//server host
private String mailServerHost;
//server port
private String mailServerPort;
//from
private String fromAddress;
//to
private String toAddress;
// username & password
private String userName;
private String passWord;
// 身份验证
private boolean validate = false;
//subject
private String subject;
//content
private String content;

//attachFile
private String attachFile;

/**
 * 获得邮件会话属性
 */

public Properties getProperties(){
	Properties p = new Properties();
    p.put("mail.smtp.host", this.mailServerHost);
    p.put("mail.smtp.port", this.mailServerPort);
    p.put("mail.smtp.auth", validate? "true" :"false");
    p.put("mail.smtp.starttls.enable", "true");
    p.put("mail.smtp.EnableSSL.enable", "true");
    p.put("mail.smtp.socketFactory.clas", "javax.net.ssl.SSLSocketFactory");
    p.put("mail.smtp.socketFactory.fallback", "false");
    
    return p;
    }

public String getMailServerHost() {
	return mailServerHost;
}

public void setMailServerHost(String mailServerHost) {
	this.mailServerHost = mailServerHost;
}

public String getMailServerPort(){
	return mailServerPort;
}

public void setMailServerPort(String mailServerPort) {
	this.mailServerPort = mailServerPort;
}

public String getFromAddress() {
	return fromAddress;
}

public void setFromAddress(String fromAddress) {
	this.fromAddress = fromAddress;
}

public String getToAddress() {
	return toAddress;
}

public void setToAddress(String toAddress) {
	this.toAddress = toAddress;
}

public String getUserName() {
	return userName;
}

public void setUserName(String userName) {
	this.userName = userName;
}

public String getPassWord() {
	return passWord;
}

public void setPassWord(String passWord) {
	this.passWord = passWord;
}

public boolean isValidate() {
	return validate;
}

public void setValidate(boolean validate) {
	this.validate = validate;
}

public String getSubject() {
	return subject;
}

public void setSubject(String subject) {
	this.subject = subject;
}

public String getContent() {
	return content;
}

public void setContent(String content) {
	this.content = content;
}

/**
 * @return the attachFile
 */
public String getAttachFile() {
	return attachFile;
}

/**
 * @param attachFile the attachFile to set
 */
public void setAttachFile(String attachFile) {
	this.attachFile = attachFile;
}


}
