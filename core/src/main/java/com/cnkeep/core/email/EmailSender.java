package com.cnkeep.core.email;

import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import com.cnkeep.core.email.entity.EmailAttach;
import com.cnkeep.core.email.entity.EmailImage;
import com.cnkeep.core.email.entity.EmailParam;
import com.sun.mail.util.MailSSLSocketFactory;

/**
 * 邮件发送
 * 
 * @description
 * @author <a href="zhangleili@wxchina.com">LeiLi.Zhang</a>
 * @date 2017年11月21日
 */
public class EmailSender {

	/** 邮件内容格式 **/
	private final static String EMAIL_TYPE = "text/html;charset=";

	private final static String SMTP = "smtp";
	private final static String SMTPS = "smtps";

	/**
	 * @description 发送邮件，外部调用接口
	 * @author <a href="mailto:zhangleili@wxchian.com">LeiLi.Zhang</a>
	 * @param emailParam
	 *            发送相关参数实体，包含：发送主机，登录信息
	 * @throws NoSuchProviderException
	 * @throws MessagingException
	 * @throws Exception
	 */
	public static void send(EmailParam emailParam) throws NoSuchProviderException, MessagingException, Exception {
		Properties prop = init(emailParam);
		Session session = getSession(prop);
		sendMsg(session, emailParam);
	}

	/**
	 * @description 初始化服务器信息
	 * @author <a href="mailto:zhangleili@wxchian.com">LeiLi.Zhang</a>
	 * @param emailParam
	 * @return
	 * @throws GeneralSecurityException
	 */
	private static Properties init(EmailParam emailParam) throws GeneralSecurityException {
		Properties prop = new Properties();
		prop.setProperty("mail.host", emailParam.getHost());
		prop.setProperty("mail.transports.port", ""+emailParam.getPort());
		prop.setProperty("mail.transports.protocol", emailParam.getProtocol().getProtocol());
		prop.setProperty("mail.smtp.auth", emailParam.getIsSSL());
		prop.setProperty("debugger", emailParam.getDebugger());
		if (Boolean.parseBoolean(emailParam.getIsSSL())) {
			MailSSLSocketFactory sf = new MailSSLSocketFactory();
			sf.setTrustAllHosts(true);

			prop.put("mail.smtp.ssl.enable", "true");
			prop.put("mail.smtp.ssl.socketFactory", sf);
		}
		return prop;
	}

	private static Session getSession(Properties prop) {
		// 1、创建session
		Session session = Session.getInstance(prop);
		// 开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
		session.setDebug(Boolean.parseBoolean(prop.getProperty("debugger")));
		return session;
	}

	/**
	 * @description 发送邮件
	 * @author <a href="mailto:zhangleili@wxchian.com">LeiLi.Zhang</a>
	 * @param session
	 * @param emailParam
	 * @throws NoSuchProviderException
	 * @throws MessagingException
	 * @throws Exception
	 */
	private static void sendMsg(Session session, EmailParam emailParam)
			throws NoSuchProviderException, MessagingException, Exception {
		Transport ts = session.getTransport(Boolean.valueOf(emailParam.getIsSSL()) ? SMTPS : SMTP);
		// 使用邮箱的用户名和密码连上邮件服务器，发送邮件时，发件人需要提交邮箱的用户名和密码给smtp服务器，用户名和密码都通过验证之后才能够正常发送邮件给收件人。
		ts.connect(emailParam.getHost(), emailParam.getPort(), emailParam.getUser(), emailParam.getPassword());
		// 创建邮件
		Message message = createMail(session, emailParam);
		// 发送邮件
		ts.sendMessage(message, message.getAllRecipients());
		ts.close();
	}

	/**
	 * @description 创建邮件主体
	 * @author <a href="mailto:zhangleili@wxchian.com">LeiLi.Zhang</a>
	 * @param session
	 * @param param
	 * @return
	 * @throws AddressException
	 * @throws MessagingException
	 */
	private static MimeMessage createMail(Session session, EmailParam param)
			throws AddressException, MessagingException {
		MimeMessage message = new MimeMessage(session);
		// 设置发送人信息
		message.setFrom(new InternetAddress(param.getUser()));
		// 设置收件人信息列表
		message.addRecipients(Message.RecipientType.TO, getRecAddress(param));
		// 设置抄送人信息列表
		message.addRecipients(Message.RecipientType.CC, getCCRecAddress(param));
		// 设置秘密抄送人信息列表
		message.addRecipients(Message.RecipientType.BCC, getBCCRecAddress(param));

		// 设置主题
		message.setSubject(param.getContent().getSubject(), param.getCharset());
		// 设置邮件内容
		message.setContent(initMultipart(param));
		message.saveChanges();
		return message;
	}

	/**
	 * @description 初始化邮件内容
	 * @author <a href="mailto:zhangleili@wxchian.com">LeiLi.Zhang</a>
	 * @param param
	 * @return
	 * @throws MessagingException
	 */
	private static MimeMultipart initMultipart(EmailParam param) throws MessagingException {
		MimeMultipart multPart = new MimeMultipart();
		multPart.setSubType("related");
		addBodyPart(multPart, param);
		addImagePart(multPart, param);
		addAttachPart(multPart, param);
		return multPart;
	}

	/**
	 * @description 设置邮件体中的文本内容
	 * @author <a href="mailto:zhangleili@wxchian.com">LeiLi.Zhang</a>
	 * @param multPart
	 * @param param
	 * @throws MessagingException
	 */
	private static void addBodyPart(MimeMultipart multPart, EmailParam param) throws MessagingException {
		MimeBodyPart text = new MimeBodyPart();
		text.setContent(param.getContent().getContent(), EMAIL_TYPE + param.getCharset());
		multPart.addBodyPart(text);
	}

	/**
	 * @description 设置邮件体中的图片内容
	 * @author <a href="mailto:zhangleili@wxchian.com">LeiLi.Zhang</a>
	 * @param multPart
	 * @param param
	 * @throws MessagingException
	 */
	private static void addImagePart(MimeMultipart multPart, EmailParam param) throws MessagingException {
		Map<String, EmailImage> images = param.getContent().getImages();

		if (null != images && !images.isEmpty()) {
			for (Map.Entry<String, EmailImage> image : images.entrySet()) {
				// 准备图片数据
				MimeBodyPart imagePart = new MimeBodyPart();
				DataHandler dh = new DataHandler(
						new ByteArrayDataSource(image.getValue().getData(), "application/octet-stream"));
				imagePart.setDataHandler(dh);
				// 设置图片对应内容id
				imagePart.setContentID(image.getKey());
				multPart.addBodyPart(imagePart);
			}
		}
	}

	/**
	 * @description 设置邮件体中的附件内容
	 * @author <a href="mailto:zhangleili@wxchian.com">LeiLi.Zhang</a>
	 * @param multPart
	 * @param param
	 * @throws MessagingException
	 */
	private static void addAttachPart(MimeMultipart multPart, EmailParam param) throws MessagingException {
		List<EmailAttach> attaches = param.getContent().getAttaches();
		if (attaches != null && !attaches.isEmpty()) {
			for (EmailAttach emailAttach : attaches) {
				// 创建邮件附件
				MimeBodyPart attachBody = new MimeBodyPart();
				DataHandler dh = new DataHandler(
						new ByteArrayDataSource(emailAttach.getData(), "application/octet-stream"));
				attachBody.setDataHandler(dh);
				attachBody.setFileName(emailAttach.getFileName());
				multPart.addBodyPart(attachBody);
			}
		}
	}

	/**
	 * @description 获取收件人列表
	 * @author <a href="mailto:zhangleili@wxchian.com">LeiLi.Zhang</a>
	 * @param param
	 * @return
	 * @throws AddressException
	 */
	private static InternetAddress[] getRecAddress(EmailParam param) throws AddressException {
		if (param.getReceviceAddress() == null) {
			throw new IllegalArgumentException("收件人列表不能为空");
		}
		int size = param.getReceviceAddress().size();
		InternetAddress[] recAddress = new InternetAddress[size];

		for (int index = 0; index < size; index++) {
			recAddress[index] = new InternetAddress(param.getReceviceAddress().get(index));
		}
		return recAddress;
	}

	/**
	 * @description 获取抄送人列表
	 * @author <a href="mailto:zhangleili@wxchian.com">LeiLi.Zhang</a>
	 * @param param
	 * @return
	 * @throws AddressException
	 */
	private static InternetAddress[] getCCRecAddress(EmailParam param) throws AddressException {
		if (param.getCcAddress() == null) {
			return new InternetAddress[0];
		}

		int size = param.getCcAddress().size();
		InternetAddress[] recAddress = new InternetAddress[size];

		for (int index = 0; index < size; index++) {
			recAddress[index] = new InternetAddress(param.getCcAddress().get(index));
		}
		return recAddress;
	}

	/**
	 * @description 获取私密收件人列表
	 * @author <a href="mailto:zhangleili@wxchian.com">LeiLi.Zhang</a>
	 * @param param
	 * @return
	 * @throws AddressException
	 */
	private static InternetAddress[] getBCCRecAddress(EmailParam param) throws AddressException {
		if (param.getBccAddress() == null) {
			return new InternetAddress[0];
		}
		int size = param.getBccAddress().size();
		InternetAddress[] recAddress = new InternetAddress[size];

		for (int index = 0; index < size; index++) {
			recAddress[index] = new InternetAddress(param.getBccAddress().get(index));
		}
		return recAddress;
	}
}