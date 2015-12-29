package cc.wechat.handle;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cc.wechat.sdk.handle.MessageHandle;
import cc.wechat.sdk.message.BaseMsg;
import cc.wechat.sdk.message.TextMsg;
import cc.wechat.sdk.message.req.BaseReqMsg;
import cc.wechat.sdk.message.req.TextReqMsg;
import cc.wechat.service.context.ContextService;
import cc.wechat.service.context.bean.ReqContext;

/**
 * 默认文本消息处理器
 * 
 * <pre>
 * 处理默认的文本回复
 * </pre>
 * 
 * @author weny
 * @datetime 2015年12月29日 下午1:48:34
 */
@Component("defaultMessageHandle")
public class DefaultMessageHandle implements MessageHandle<BaseReqMsg> {

	@Autowired
	private ContextService contextService;

	@Override
	public BaseMsg handle(BaseReqMsg message) {
		// TODO Auto-generated method stub
		System.err.println("*****************cc.wechat.handle.DefaultMessageHandle<BaseReqMsg>****************");
		ReqContext context = contextService.getContext(message.getFromUserName());
		if(context != null && !context.getIsActive()) {
			return null;
		}
		if (message instanceof TextReqMsg) {
			TextReqMsg reqMsg = (TextReqMsg) message;
			String inStr = reqMsg.getContent();
			String outStr = null;
			if (StringUtils.isNotEmpty(inStr)) {
				if ("m".equalsIgnoreCase(inStr)) {
					outStr = contextService.getMenuText();
				}
				if ("h".equalsIgnoreCase(inStr)) {
					outStr = contextService.getHelpText();
				}
				if ("q".equalsIgnoreCase(inStr)) {
					context.setIsActive(false);
				}
			}
			if (StringUtils.isNotEmpty(outStr)) {
				TextMsg msg = new TextMsg();
				msg.add(outStr);
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
