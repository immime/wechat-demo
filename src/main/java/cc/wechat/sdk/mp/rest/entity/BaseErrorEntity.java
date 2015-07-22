package cc.wechat.sdk.mp.rest.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseErrorEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3878822002878917752L;
	private String errcode;
	private String errmsg;

	public BaseErrorEntity() {
	}

	public BaseErrorEntity(String errcode, String errmsg) {
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
