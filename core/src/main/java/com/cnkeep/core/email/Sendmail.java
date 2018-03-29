package com.cnkeep.core.email;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.UUID;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.cnkeep.core.email.entity.EmailAttach;
import com.cnkeep.core.email.entity.EmailImage;

/**
 * @ClassName: Sendmail
 * @Description: 发送Email
 * @author: 孤傲苍狼
 * @date: 2015-1-12 下午9:42:56
 *
 */
public class Sendmail {

	/**
	 * @Method: createSimpleMail
	 * @Description: 创建一封只包含文本的邮件
	 * @Anthor:孤傲苍狼
	 * @param session
	 * @return
	 * @throws Exception
	 */
	public static MimeMessage createSimpleMail(Session session) throws Exception {
		// 创建邮件对象
		MimeMessage message = new MimeMessage(session);
		// 指明邮件的发件人
		message.setFrom(new InternetAddress("zhangleili@cnkeep.cn"));
		// 指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
		message.setRecipient(Message.RecipientType.TO, new InternetAddress("1348555156@qq.com"));
		// 邮件的标题
		message.setSubject("只包含文本的简单邮件");
		// 邮件的文本内容
		message.setContent("你好啊！", "text/html;charset=UTF-8");
		// 返回创建好的邮件对象
		return message;
	}

	/**
	 * @Method: createImageMail
	 * @Description: 生成一封邮件正文带图片的邮件
	 * @Anthor:孤傲苍狼
	 *
	 * @param session
	 * @return
	 * @throws Exception
	 */
	public static MimeMessage createImageMail(Session session) throws Exception {
		// 创建邮件
		MimeMessage message = new MimeMessage(session);
		// 设置邮件的基本信息
		// 发件人
		message.setFrom(new InternetAddress("zhangleili@cnkeep.cn"));
		// 收件人
		message.setRecipient(Message.RecipientType.TO, new InternetAddress("1348555156@qq.com"));
		// 邮件标题
		message.setSubject("带图片的邮件");

		// 准备邮件数据
		// 准备邮件正文数据
		MimeBodyPart text = new MimeBodyPart();
		text.setContent("这是一封邮件正文带图片<img src='cid:xxx.jpg'>的邮件", "text/html;charset=UTF-8");
		// 准备图片数据
		MimeBodyPart image = new MimeBodyPart();
		DataHandler dh = new DataHandler(new FileDataSource("src/main/resources/136.gif"));
		image.setDataHandler(dh);
		image.setContentID("xxx.jpg");
		// 描述数据关系
		MimeMultipart mm = new MimeMultipart();
		mm.addBodyPart(text);
		mm.addBodyPart(image);
		mm.setSubType("related");

		message.setContent(mm);
		message.saveChanges();
		// 将创建好的邮件写入到E盘以文件的形式进行保存
		// message.writeTo(new FileOutputStream("E:\\ImageMail.eml"));
		// 返回创建好的邮件
		return message;
	}

	/**
	 * @Method: createAttachMail
	 * @Description: 创建一封带附件的邮件
	 * @Anthor:孤傲苍狼
	 *
	 * @param session
	 * @return
	 * @throws Exception
	 */
	public static MimeMessage createAttachMail(Session session) throws Exception {
		MimeMessage message = new MimeMessage(session);

		// 设置邮件的基本信息
		// 发件人
		message.setFrom(new InternetAddress("zhangleili@cnkeep.cn"));
		// 收件人
		message.setRecipient(Message.RecipientType.TO, new InternetAddress("1348555156@qq.com"));
		// 邮件标题
		message.setSubject("JavaMail邮件发送测试");

		// 创建邮件正文，为了避免邮件正文中文乱码问题，需要使用charset=UTF-8指明字符编码
		MimeBodyPart text = new MimeBodyPart();
		text.setContent("使用JavaMail创建的带附件的邮件", "text/html;charset=UTF-8");

		// 创建邮件附件
		MimeBodyPart attach = new MimeBodyPart();
		DataHandler dh = new DataHandler(new FileDataSource("src/main/resources/system.properties"));
		attach.setDataHandler(dh);
		attach.setFileName(dh.getName()); //

		// 创建容器描述数据关系
		MimeMultipart mp = new MimeMultipart();
		mp.addBodyPart(text);
		mp.addBodyPart(attach);
		mp.setSubType("mixed");

		message.setContent(mp);
		message.saveChanges();
		// 将创建的Email写入到E盘存储
		message.writeTo(new FileOutputStream("E:\\attachMail.eml"));
		// 返回生成的邮件
		return message;
	}

	/**
	 * @description 测试实例
	 * @author <a href="mailto:zhangleili@wxchian.com">LeiLi.Zhang</a>
	 * @date 2017年11月21日
	 * @Version 1.0.0
	 * @param args
	 * @throws Exception
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {

		com.cnkeep.core.email.entity.EmailParam emailParam = new com.cnkeep.core.email.entity.EmailParam();
		emailParam.setIsSSL("true");
		emailParam.setPort(465);
		//收件人列表
		emailParam.getReceviceAddress().add("1348555156@qq.com");
		//抄送人列表
		//emailParam.getCcAddress().add("1477063532@qq.com");
		
		com.cnkeep.core.email.entity.EmailContent content = emailParam.getContent();
		content.setContent("<p>您好，您当前正在使用邮箱注册<a href=\"http://www.baidu.com\">智云</a>账号</p>，点击<a href=\"\">立即激活</a> 使用吧！<h1><u>789561</u></h1>正文带图片<img src='cid:1.jpg'>的邮件");
		content.setSubject("激活账号");
		
		/**
		 * 初始化邮件图片
		 */
		EmailImage value = new EmailImage();
		InputStream is = new FileInputStream("src/main/resources/136.gif");
		byte[] data = new byte[is.available()];
		is.read(data);
		value.setData(data);
		//添加图片到正文
		content.getImages().put(UUID.randomUUID().toString(), value);
		
		/**
		 * 初始化邮件附件
		 */
		is = new FileInputStream("src/main/resources/system.properties");
		data = new byte[is.available()];
		is.read(data);
		is.close();
		EmailAttach attach = new EmailAttach();
		attach.setData(data);
		attach.setFileName("system.properties");
		content.getAttaches().add(attach);
		
		//发送邮件
		EmailSender.send(emailParam);
	}
}