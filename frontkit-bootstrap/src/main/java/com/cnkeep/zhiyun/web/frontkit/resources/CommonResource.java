package com.cnkeep.zhiyun.web.frontkit.resources;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import com.cnkeep.zhiyun.web.common.domain.JsonResp;
import com.cnkeep.zhiyun.web.common.util.RequestUtil;
/**
 * @Description 公共接口
 * @author <a href="mailto:zhangleili@wxchina.com">LeiLi.Zhang</a>
 * @Date 2017年11月28日
 * @Version 0.0.0
 */
@Component
@Path("common")
public class CommonResource {

	/**
	 * @description 未授权访问的重定向接口
	 * @author <a href="mailto:zhangleili@wxchian.com">LeiLi.Zhang</a>
	 * @return
	 */
	@GET
	@Path("unauthorized")
	@Produces(value = { MediaType.APPLICATION_JSON })
	public JsonResp unauthorized(@Context HttpServletResponse response){
		response.addHeader(RequestUtil.ACCESS_CONTROLL,RequestUtil.UNAUTHORIZATION);
		return JsonResp.fail("un_authorizaiton");
	}
	
//	
//	/**
//	 * @description 未授权访问的重定向接口
//	 * @author <a href="mailto:zhangleili@wxchian.com">LeiLi.Zhang</a>
//	 * @return
//	 */
//	@GET
//	@Path("unauthorized")
//	@Produces(value = { MediaType.APPLICATION_JSON })
//	public Response unauthorized(){
//		return Response.ok().header(RequestUtil.ACCESS_CONTROLL,RequestUtil.UNAUTHORIZATION).build();
//	}
//	
	
}
