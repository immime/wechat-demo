package cc.wechat.sdk.bean.msg.out;

import cc.wechat.sdk.bean.msg.BaseMsg;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * api请求出参基类
 * @author weny
 * @datetime 2015年7月24日 上午10:57:29
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseOutMsg  extends BaseMsg {

	static final long serialVersionUID = 597432974313374735L;
	/**
	 * 调用接口凭证
	 */
	private String access_token;
	/**
	 * 普通用户openid
	 */
	private String touser;
	/**
	 * 消息类型，text
	 */
	private String msgtype;

	public BaseOutMsg() {
	}

	public BaseOutMsg(String access_token, String touser, String msgtype) {
		super();
		this.access_token = access_token;
		this.touser = touser;
		this.msgtype = msgtype;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getTouser() {
		return touser;
	}

	public void setTouser(String touser) {
		this.touser = touser;
	}

	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}

}
