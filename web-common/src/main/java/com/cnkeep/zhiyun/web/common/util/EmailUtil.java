package com.cnkeep.zhiyun.web.common.util;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;

import com.cnkeep.core.email.EmailSender;

public class EmailUtil {
	public static void sendVerfiyEmail(String emailAddress,String activeUrl) throws NoSuchProviderException, MessagingException, Exception{
		com.cnkeep.core.email.entity.EmailParam emailParam = new com.cnkeep.core.email.entity.EmailParam();
		emailParam.setIsSSL("true");
		emailParam.setDebugger("false");
		emailParam.setPort(465);
		//收件人列表
		emailParam.getReceviceAddress().add(emailAddress);
		
		com.cnkeep.core.email.entity.EmailContent content = emailParam.getContent();
		content.setContent("<p>您好：<br>您当前正在使用邮箱注册<a href=\"http://www.baidu.com\"><智云</a>账号，点击<a href=\""+activeUrl+"\">立即激活</a> 使用吧！48小时内有效</p>");
		content.setSubject("激活账号");
		//发送邮件
		EmailSender.send(emailParam);
	}
}
