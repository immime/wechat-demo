package cc.wechat.service.context.bean;

import cc.wechat.sdk.message.req.BaseReq;

/**
 * 关注者在微信会话中的上下文
 * @author weny
 * @datetime 2015年12月7日 上午10:21:11
 */
public class WeixinContext {
	/**
	 * 关注者openId
	 */
	private String openId;
	/**
	 * 关注者上一条消息
	 */
	private BaseReq lastReqMsg;
	
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public BaseReq getLastMsg() {
		return lastReqMsg;
	}
	public void setLastMsg(BaseReq lastMsg) {
		this.lastReqMsg = lastMsg;
	}
	
}
