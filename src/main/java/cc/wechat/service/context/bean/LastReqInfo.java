package cc.wechat.service.context.bean;

import java.io.Serializable;

import cc.wechat.sdk.message.req.BaseReq;


/**
 * 关注者在微信会话上下文中上一次请求信息
 * @author weny
 * @datetime 2015年12月7日 上午10:21:11
 */
public class LastReqInfo implements Serializable {
	private static final long serialVersionUID = -4035906123516189719L;
	
	private String fromUserName;
    private String reqMsgType;
    private BaseReq reqMsg;
    
    public LastReqInfo() {
	}
	public LastReqInfo(String fromUserName, String reqMsgType, BaseReq reqMsg) {
		super();
		this.fromUserName = fromUserName;
		this.reqMsgType = reqMsgType;
		this.reqMsg = reqMsg;
	}

	public String getFromUserName() {
		return fromUserName;
	}
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	public String getReqMsgType() {
		return reqMsgType;
	}
	public void setReqMsgType(String reqMsgType) {
		this.reqMsgType = reqMsgType;
	}
	public BaseReq getReqMsg() {
		return reqMsg;
	}
	public void setReqMsg(BaseReq reqMsg) {
		this.reqMsg = reqMsg;
	}
	
}