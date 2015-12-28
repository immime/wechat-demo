package cc.wechat.handle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cc.wechat.sdk.handle.MessageHandle;
import cc.wechat.sdk.message.BaseMsg;
import cc.wechat.sdk.message.TextMsg;
import cc.wechat.sdk.message.req.BaseReq;
import cc.wechat.sdk.message.req.BaseReqMsg;
import cc.wechat.sdk.message.req.ReqType;
import cc.wechat.sdk.message.req.TextReqMsg;
import cc.wechat.service.context.ContextService;
import cc.wechat.service.context.bean.LastReqInfo;

@Component
public class ContextMessageHandle implements MessageHandle<BaseReqMsg> {
	
	@Autowired
	private ContextService contextService;
	
	@Override
	public BaseMsg handle(BaseReqMsg message) {
		// TODO Auto-generated method stub
		System.err.println("*****************cc.wechat.handle.MenuContextMessageHandle****************");
		if(message instanceof TextReqMsg) {
			LastReqInfo lastReq = contextService.getLastReqMsg(message.getFromUserName());
			if(lastReq == null) {
				return null;
			}
			ReqType reqType =lastReq.getReqMsgType();
			if(reqType == ReqType.text) {
				BaseReq reqMsg = lastReq.getReqMsg();
				TextMsg msg = new TextMsg();
				msg.add("被拦截：" + reqMsg.getCreateTime());
				return msg;
			}
			
		}
		return null;
	}

	@Override
	public boolean beforeHandle(BaseReqMsg message) {
		// return true
		return true;
	}

}
