package cc.wechat.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cc.wechat.handle.TestConfigChangeHandle;
import cc.wechat.sdk.api.config.ApiConfig;

@Component
public class ApiConfigCenter {
	
	@Autowired
	private WechatProperties wechatProperties;
	
	public ApiConfig getConfig() {
		ApiConfig config = new ApiConfig(wechatProperties.getAppID(), wechatProperties.getAppSecret());
		TestConfigChangeHandle configChangeHandle = new TestConfigChangeHandle();
		config.addHandle(configChangeHandle);
		return config;
	}
	
	
}
