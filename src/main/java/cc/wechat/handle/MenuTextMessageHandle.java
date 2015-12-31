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
			String inStr = inMsg.getContent();
			String code = inStr.concat("_");
			
			ReqContext context = contextService.getContext(message.getFromUserName());
			if(context == null) {
				if(inStr.equals("m")) {
					code = "0_1_";
				}
				if(inStr.equals("h")) {
					code = "0_2_";
				}
				
				ContextMenu direct = menuNavigate.direct(code);
				if(direct == null) {
					return null;
				}
				String outStr = contextService.getMenuText(direct).concat("\n");
				TextMsg outMsg = new TextMsg();
				outMsg.add(outStr);
				return outMsg;
			}
			
			if (StringUtils.isNotEmpty(inStr)) {
				ContextMenu menu = context.getMenu();
				if (menu == null) {
					String outStr = null;
					ContextMenu direct = menuNavigate.direct(code);
					if (direct == null) {
						outStr = contextService.getMenuText(menu).concat("\n").concat("请选择正确的序号");
					} else {
						outStr = process(inStr, context, menu);
					}
					TextMsg outMsg = new TextMsg();
					outMsg.add(outStr);
					return outMsg;
				} else {
					return null;
				}
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
