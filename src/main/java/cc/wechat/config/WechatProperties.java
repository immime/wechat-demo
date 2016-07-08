package cc.wechat.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "wechat", locations = "classpath:config/wechat.properties")
public class WechatProperties {
	private String appID;
	private String appSecret;
	private String token;
	private String aesKey;
	private Boolean enableJsApi;
	
	public String getAppID() {
		return appID;
	}
	public void setAppID(String appID) {
		this.appID = appID;
	}
	public String getAppSecret() {
		return appSecret;
	}
	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getAesKey() {
		return aesKey;
	}
	public void setAesKey(String aesKey) {
		this.aesKey = aesKey;
	}
	public Boolean getEnableJsApi() {
		return enableJsApi;
	}
	public void setEnableJsApi(Boolean enableJsApi) {
		this.enableJsApi = enableJsApi;
	}
	
}
