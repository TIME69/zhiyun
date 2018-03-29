package com.cnkeep.zhiyun.web.frontkit.resources;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileSystemUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import com.cnkeep.zhiyun.web.common.domain.JsonResp;

/**
 * @description 附件上传下载接口
 * @author <a href="zhangleili@wxchina.com">LeiLi.Zhang</a>
 * @date 2018年1月5日
 */
@Component
@Path("attachment")
public class AttachmentResource {

	@Path("upload")
	@POST
	// @Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public JsonResp uploadFile(@Context HttpServletRequest request) {
		
//		upload(request);
		String message = "";
		return JsonResp.success(message);
	}


	public void display(@Context HttpServletRequest request, @Context HttpServletResponse response) {

	}
}
