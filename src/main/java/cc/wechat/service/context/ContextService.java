package cc.wechat.service.context;

import org.springframework.stereotype.Service;

import cc.wechat.service.context.bean.WeixinContext;

@Service
public class ContextService implements IContextService {

	@Override
	public void setContext(String openId, WeixinContext ctx) {
		// TODO Auto-generated method stub
		ctx.setOpenId(openId);
		
		
	}

	@Override
	public WeixinContext getContext(String openId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void invalid(String openId) {
		// TODO Auto-generated method stub

	}

}
