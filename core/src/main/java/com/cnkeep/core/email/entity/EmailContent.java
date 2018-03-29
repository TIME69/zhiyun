package com.cnkeep.core.email.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 邮件内容实体类
 * 
 * @description
 * @author <a href="zhangleili@wxchina.com">LeiLi.Zhang</a>
 * @date 2017年11月20日
 */
public class EmailContent {
	/** 主题 **/
	private String subject;
	
	/**
	 * 文本内容
	 */
	private String content;
	
	/**
	 * 图片内容
	 */
	private Map<String, EmailImage> images = new HashMap<String,EmailImage>();
	
	/**
	 * 附件列表
	 */
	private List<EmailAttach> attaches = new ArrayList<EmailAttach>();

	public String getSubject() {
		return subject;
	}

	/**
	 * @description 设置主题内容 
	 * @author <a href="mailto:zhangleili@wxchian.com">LeiLi.Zhang</a>
	 * @param subject
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @description 获取邮件内容 
	 * @author <a href="mailto:zhangleili@wxchian.com">LeiLi.Zhang</a>
	 * @return
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @description 设置邮件内容 
	 * @author <a href="mailto:zhangleili@wxchian.com">LeiLi.Zhang</a>
	 * @param content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @description 获取图片资源 
	 * @author <a href="mailto:zhangleili@wxchian.com">LeiLi.Zhang</a>
	 * @return
	 */
	public Map<String, EmailImage> getImages() {
		return images;
	}

	/**
	 * @description 设置图片资源 
	 * @author <a href="mailto:zhangleili@wxchian.com">LeiLi.Zhang</a>
	 * @param images
	 */
	public void setImages(Map<String, EmailImage> images) {
		this.images = images;
	}

	/**
	 * @description 获取附件列表 
	 * @author <a href="mailto:zhangleili@wxchian.com">LeiLi.Zhang</a>
	 * @return
	 */
	public List<EmailAttach> getAttaches() {
		return attaches;
	}

	/**
	 * @description 设置附件列表 
	 * @author <a href="mailto:zhangleili@wxchian.com">LeiLi.Zhang</a>
	 * @param attaches
	 */
	public void setAttaches(List<EmailAttach> attaches) {
		this.attaches = attaches;
	}
}
