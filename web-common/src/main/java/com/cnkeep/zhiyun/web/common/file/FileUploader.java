package com.cnkeep.zhiyun.web.common.file;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/***
 * @description 文件上传 
 * @author <a href="zhangleili@wxchina.com">LeiLi.Zhang</a>
 * @date 2018年1月12日
 */
public class FileUploader {
	private static final Logger logger = LoggerFactory.getLogger(FileUploader.class);
	
	public static void upload(HttpServletRequest request) {
		try {
			// Create a factory for disk-based file items
			FileItemFactory factory = new DiskFileItemFactory();
			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);
			// Set overall request size constraint
			//upload.setSizeMax();
			upload.setHeaderEncoding("utf-8");
			List<FileItem> fileItems = upload.parseRequest(request);
			for (FileItem fileItem : fileItems) {
				if(!fileItem.isFormField()){
//					String fieldName = fileItem.getFieldName();
					byte[] data = fileItem.get();
					String name = fileItem.getName();
					FileUtils.writeByteArrayToFile(new File("E://"+name), data);
				}
			}
		} catch (FileUploadException | IOException e) {
			logger.error("fileupload error cause by:{}",e);
		}
	}
	
	
	
}
