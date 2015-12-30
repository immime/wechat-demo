package cc.wechat.handle;

import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cc.wechat.data.domain.ContextMenu;
import cc.wechat.sdk.handle.MessageHandle;
import cc.wechat.sdk.message.BaseMsg;
import cc.wechat.sdk.message.TextMsg;
import cc.wechat.sdk.message.req.BaseReqMsg;
import cc.wechat.sdk.message.req.TextReqMsg;
import cc.wechat.service.context.ContextService;
import cc.wechat.service.context.bean.ReqContext;
import cc.wechat.service.menu.MenuNavigate;

/**
 * 文本菜单处理器
 * 
 * <pre>
 * 处理用户菜单轨迹
 * </pre>
 * 
 * @author weny
 * @datetime 2015年12月29日 下午1:46:04
 */
@Component(value="menuTextMessageHandle")
public class MenuTextMessageHandle implements MessageHandle<BaseReqMsg> {

	@Autowired
	private ContextService contextService;
	@Autowired
	private MenuNavigate menuNavigate;

	@Override
	public BaseMsg handle(BaseReqMsg message) {
		// TODO Auto-generated method stub
		System.err.println("*****************cc.wechat.handle.MenuContextMessageHandle****************");
		
		if (message instanceof TextReqMsg) {
			TextReqMsg inMsg = (TextReqMsg) message;
			
			ReqContext context = contextService.getContext(message.getFromUserName());
			if(context == null) {
				context = new ReqContext(inMsg.getFromUserName(), inMsg.getMsgType(), inMsg);
			}
			
			String inStr = inMsg.getContent();
			String outStr = null;
			if (StringUtils.isNotEmpty(inStr)) {
				if ("m".equalsIgnoreCase(inStr)) {
					ContextMenu main = menuNavigate.direct("0_1_");
					outStr = contextService.getMenuText(main);
					context.setMenu(main);
					contextService.updateContext(context);
				}
				else if ("h".equalsIgnoreCase(inStr)) {
					ContextMenu help = menuNavigate.direct("0_2_");
					outStr = contextService.getMenuText(help);
					context.setMenu(help);
					contextService.updateContext(context);
				}
				else if ("q".equalsIgnoreCase(inStr)) {
					context.setMenu(null);
					contextService.updateContext(context);
					outStr = "已退出回话！";
				}
				else {
					ContextMenu menu = context.getMenu();
					if(menu == null) {
						return null;
					}
					outStr = process(inStr, context, menu);
					if(StringUtils.isEmpty(outStr)) {
						outStr = contextService.getMenuText(menu).concat("\n").concat("请选择正确的序号");
					}
				}
				TextMsg outMsg = new TextMsg();
				outMsg.add(outStr);
				return outMsg;
			}

		}
		return null;
	}
	
	private String process(String inStr, ReqContext context, ContextMenu menu) {
		String outStr = null;
		Set<ContextMenu> children = menu.getChildren();
		if(CollectionUtils.isNotEmpty(children)) {
			ContextMenu next = menuNavigate.next(menu.getCode(), inStr.concat("_"));
			if(next == null) {
				outStr = contextService.getMenuText(menu);
				context.setMenu(menu);
			} else {
				outStr = contextService.getMenuText(next);
				context.setMenu(next);
			}
		} else {
			if (menu.getNode().equals(inStr)) {
				context.setMenu(menu.getParent());
				// TODO do endpiont handle stuff
				outStr = handleEndpoint(menu.getEndpoint());
			}
		} 	
		contextService.updateContext(context);
		return outStr;
	}

	private String handleEndpoint(String endpoint) {
		// TODO Auto-generated method stub
		return endpoint;
	}

	@Override
	public boolean beforeHandle(BaseReqMsg message) {
		// return true
		return true;
	}

}
