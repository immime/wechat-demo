package cc.wechat.handle;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cc.wechat.data.domain.ContextMenu;
import cc.wechat.sdk.handle.MessageHandle;
import cc.wechat.sdk.message.BaseMsg;
import cc.wechat.sdk.message.TextMsg;
import cc.wechat.sdk.message.req.BaseReq;
import cc.wechat.sdk.message.req.BaseReqMsg;
import cc.wechat.sdk.message.req.ReqType;
import cc.wechat.sdk.message.req.TextReqMsg;
import cc.wechat.service.context.ContextService;
import cc.wechat.service.context.bean.ReqContext;
import cc.wechat.service.menu.ContextMenuService;

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
@Component
public class MenuTextMessageHandle implements MessageHandle<BaseReqMsg> {

	@Autowired
	private ContextService contextService;
	@Autowired
	private ContextMenuService contextMenuService;

	@Override
	public BaseMsg handle(BaseReqMsg message) {
		// TODO Auto-generated method stub
		System.err.println("*****************cc.wechat.handle.MenuContextMessageHandle****************");
		ReqContext context = contextService.getContext(message.getFromUserName());
		if(context != null && context.getIsActive()) {
			// 根据面包屑处理
			String crumb = context.getCrumb();
			System.err.println("================hanle请求:" + crumb + "================");
			
			if(contextMenuService.isUnderCotextMenu(crumb)) {
				ContextMenu menu = contextMenuService.findByCode(crumb);
				TextMsg msg = new TextMsg();
				msg.add(menu.getDisplayName());
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
