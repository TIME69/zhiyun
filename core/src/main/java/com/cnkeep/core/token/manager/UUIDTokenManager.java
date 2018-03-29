package com.cnkeep.core.token.manager;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.cnkeep.core.token.entity.TokenTemplate;
import com.cnkeep.core.util.UUIDUtil;

public class UUIDTokenManager implements TokenManager {
	private TokenTemplate tokenModel;
	private ConcurrentHashMap<String, Long> cache = new ConcurrentHashMap<String, Long>();

	
	public UUIDTokenManager(TokenTemplate token) {
		super();
		this.tokenModel = token;
		startCheck();
	}

	/**
	 * @description 开启token缓存清理 
	 * @author <a href="mailto:zhangleili@wxchian.com">LeiLi.Zhang</a>
	 */
	private void startCheck() {
		new Thread(new Runnable(){
			@Override
			public void run() {
				while(true){
					for(Map.Entry<String, Long> entry:cache.entrySet()){
						if(entry.getValue()>System.currentTimeMillis()){
							cache.remove(entry.getKey());
						}
					}
				}
			}
			
		}).start();
	}

	@Override
	public String make(String target) {
		String token = UUIDUtil.uuid();
		cache.put(token, System.currentTimeMillis()+tokenModel.getExpired());
		return token;
	}

	@Override
	public int check(String token) {
		return cache.containsKey(token) ? 0 : -1;
	}

	
	
}
