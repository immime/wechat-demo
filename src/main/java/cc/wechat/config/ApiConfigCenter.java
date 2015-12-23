package cc.wechat.config;

import cc.wechat.handle.TestConfigChangeHandle;
import cc.wechat.sdk.api.config.ApiConfig;

public class ApiConfigCenter {
	
	public static ApiConfig getCongig() {
		String appid = "wx8c33ff895df5d0d9";
		String secret = "0705aafac0bef944de4c485d71fce900";
		ApiConfig config = new ApiConfig(appid, secret);
		TestConfigChangeHandle configChangeHandle = new TestConfigChangeHandle();
		config.addHandle(configChangeHandle);
		return config;
	}
	
	
}
