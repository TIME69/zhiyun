package com.cnkeep.zhiyun.web.frontkit.resources;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import org.springframework.stereotype.Component;

import com.cnkeep.zhiyun.web.common.util.StringUtil;

/**
 * @Description 提供文件下载的访问接口
 * @author <a href="mailto:zhangleili@wxchina.com">LeiLi.Zhang</a>
 * @Date 2017年11月26日
 * @Version 0.0.0
 */
@Component
@Path("download")
public class DownLoadResource {
	@GET
	@Path("attachments")
	public void download(@Context HttpServletRequest request,@Context HttpServletResponse response, String fileName) throws IOException {
		response.setContentType("application/octet-stream");
		response.addHeader("Content-Disposition", "attachment; filename=\""+getFileName(request, "数据库.doc")+"\"");

		OutputStream outputStream = null;
		InputStream inputStream = null;
		try {
			inputStream = new BufferedInputStream(this.getClass().getClassLoader().getResourceAsStream("banner.txt"));
			outputStream = response.getOutputStream();
			int len = 0;
			byte[] buffer = new byte[100];
			while (-1 != (len = inputStream.read(buffer))) {
				outputStream.write(buffer, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != outputStream) {
				outputStream.close();
			}
			if (null != inputStream) {
				inputStream.close();
			}
		}

	}
	/**
	 * 获取文件名，解决浏览器下载文件名乱码问题
	 * @param request
	 * @param fileName
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String getFileName(HttpServletRequest request,String fileName) throws UnsupportedEncodingException{
		return StringUtil.changeCharset(fileName, "utf-8", "ISO8859-1");
	}
}
