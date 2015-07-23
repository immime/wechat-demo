package cc.wechat.sdk.bean.msg.in;

import cc.wechat.sdk.bean.msg.BaseMsg;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseInMsg extends BaseMsg{
	
	static final long serialVersionUID = -3878822002878917752L;
	
	/**
	 * 返回码
	 */
	private String errcode;
	/**
	 * 说明
	 */
	private String errmsg;

	public BaseInMsg() {
	}

	public BaseInMsg(String errcode, String errmsg) {
		super();
		this.errcode = errcode;
		this.errmsg = errmsg;
	}

	public String getErrcode() {
		return errcode;
	}

	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

}
