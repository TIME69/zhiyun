package com.cnkeep.zhiyun.web.frontkit.resources;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cnkeep.core.token.TokenUtil;
import com.cnkeep.zhiyun.web.common.domain.JsonResp;
import com.cnkeep.zhiyun.web.common.domain.dto.UserDTO;
import com.cnkeep.zhiyun.web.common.service.UserService;
import com.cnkeep.zhiyun.web.common.util.EmailUtil;

/**
 * @description 注册账号 
 * @author <a href="zhangleili@wxchina.com">LeiLi.Zhang</a>
 * @date 2017年12月28日
 */
@Path("common/register")
@Component
public class RegisterResource {

	@Autowired
	private UserService userService;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public JsonResp register(@Context HttpServletRequest request,UserDTO user){
		try {
			String remoteAddr = request.getRemoteAddr();
			
			String realPath1 = "http://"+ request.getServerName()+ ":"+ request.getServerPort()+ request.getContextPath()+ request.getServletPath().substring(0,request.getServletPath().lastIndexOf("/") + 1);
			System.out.println("web URL 路径:" + realPath1);
			EmailUtil.sendVerfiyEmail(user.getEmail(), "http://127.0.0.1:9090/service/common/register/active?identity=1348555156@qq.com&token="+TokenUtil.make("1348555156@qq.com", 1000*60*30));
		} catch (Exception e) {
			e.printStackTrace();
			JsonResp.fail();
		}
		return JsonResp.success("激活成功，快点登录体验吧!");
	}
	
	@GET
	@Path("active")
	@Produces(MediaType.APPLICATION_JSON)
	public JsonResp verify(@QueryParam("identify")String  identity,@QueryParam("token")String token){
		UserDTO verify = new UserDTO();
		verify.setIdentity(identity);
		verify.setToken(token);
		
		userService.verfiyToken(verify);
		return JsonResp.success("激活成功，快点登录体验吧!");
	}
}
