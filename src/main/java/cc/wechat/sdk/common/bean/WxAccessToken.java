package cc.wechat.sdk.common.bean;

import java.io.Serializable;

import cc.wechat.sdk.common.util.json.WxGsonBuilder;

public class WxAccessToken implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1885065012463224864L;

	private String accessToken;

	private int expiresIn = -1;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}

	public static WxAccessToken fromJson(String json) {
		return WxGsonBuilder.create().fromJson(json, WxAccessToken.class);
	}

}
