package cc.wechat.sdk.mp.rest.entity.msg.send;

import cc.wechat.sdk.mp.rest.entity.BaseErrorEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseSendMsg extends BaseErrorEntity {

	static final long serialVersionUID = 597432974313374735L;
	/**
	 * 普通用户openid
	 */
	private String touser;
	/**
	 * 消息类型，text
	 */
	private String msgtype;

	public BaseSendMsg() {
	}

	public BaseSendMsg(String touser, String msgtype) {
		super();
		this.touser = touser;
		this.msgtype = msgtype;
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
