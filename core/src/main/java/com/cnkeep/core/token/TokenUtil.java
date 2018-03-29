package com.cnkeep.core.token;

import com.cnkeep.core.token.entity.JWTToken;
import com.cnkeep.core.util.Base64Util;
import com.cnkeep.core.util.JsonUtil;
import com.cnkeep.core.util.MD5;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import java.io.IOException;

import com.cnkeep.core.token.entity.Header;
import com.cnkeep.core.token.entity.Payload;

/**
 * @description TOken工具类，包含的token的转换与验证
 * @author <a href="zhangleili@wxchina.com">LeiLi.Zhang</a>
 * @date 2018年1月2日
 * 
 *       <pre>
 * {
	"header":{
		"typ":"JWT",
		"alg":"MD5"
	},
	"payload":{
		"iss":"www.cnkeep.cn",
		"iat":1441593502,
		"exp":1441594755,
		"aud":"zhiyun",
		"sub":"zhiyun_user",
		"from_user":"admin",
		"target_user":""
	},
	"signature":""
}
 *       </pre>
 */

public class TokenUtil {
	/**
	 * @description 生成TOKEN
	 * @param targetUser 目标用户
	 * @param expiredTime 有效期(:ms)
	 * @return
	 * @throws JsonProcessingException
	 */
	public static String make(String targetUser, long expiredTime) throws JsonProcessingException {
		Header header = new Header(Constants.TOKEN_TYPE, Constants.TOKEN_ALG);
		Payload payload = new Payload();
		payload.setIss(Constants.TOKEN_ISS);
		long currentTimeMillis = System.currentTimeMillis();
		payload.setIat(currentTimeMillis);
		payload.setExp(currentTimeMillis + expiredTime);
		payload.setTargetUser(targetUser);
		JWTToken token = new JWTToken();
		token.setHeader(header);
		token.setPayload(payload);

		StringBuffer sb = new StringBuffer();
		sb.append(Base64Util.encode(header.toJson()));
		sb.append(Constants.DOLT);
		sb.append(Base64Util.encode(payload.toJson()));
		String signature = MD5.md5(sb.toString(), Constants.SALT);
		token.setSignature(signature);
		System.out.println(token.toJson());
		return sb.append(Constants.DOLT).append(signature).toString();
	}

	/**
	 * @description 校验token
	 * @param token
	 * @return -2：非法；-1：过期；0：合法
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public static int checkToken(String token) throws JsonParseException, JsonMappingException, IOException {
		System.out.println(token);
		String[] split = token.split("\\"+Constants.DOLT);

		if (split.length != 3)
			return -2;
		
		//校验合法性
		String headerStr = Base64Util.decode(split[0]);
		String payloadStr = Base64Util.decode(split[1]);
		StringBuffer sb = new StringBuffer(Base64Util.encode(headerStr));
		sb.append(Constants.DOLT);
		sb.append(Base64Util.encode(payloadStr));
		
		String signature = MD5.md5(sb.toString(), Constants.SALT);

		if (!split[2].equals(signature))
			return -2;
		
		Payload deserialize = JsonUtil.deserialize(payloadStr, Payload.class);
		if(deserialize.getExp()<System.currentTimeMillis()){
			return -1;
		}
		
		return 0;
	}

	public static void main(String[] args) {
		try {
			System.out.println("#.#".split("\\.").length);
			String token = make("1348555156@qq.com", 100);
			System.out.println(token);
			sleep(100);
			System.out.println(checkToken(token));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void sleep(long sleepTime) {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}