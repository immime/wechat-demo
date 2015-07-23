package cc.wechat.sdk.bean.msg;

import java.io.Serializable;

public abstract class BaseMsg implements Serializable {

	static final long serialVersionUID = -8195315926964005055L;

	/**
	 * 第三方用户唯一凭证
	 */
	private String appid;
	/**
	 * 第三方用户唯一凭证密钥，即appsecret
	 */
	private String secret;

	public BaseMsg() {
	}

	public BaseMsg(String appid, String secret) {
		super();
		this.appid = appid;
		this.secret = secret;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

}
