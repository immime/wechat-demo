package cc.wechat.sdk.bean.pay;

import java.util.Map;

import cc.wechat.sdk.bean.msg.BaseMsg;

public class OrderInfo extends BaseMsg{
	
	private static final long serialVersionUID = 532795792896562735L;
	private Map<String,String> order_info;

	public Map<String, String> getOrder_info() {
		return order_info;
	}

	public void setOrder_info(Map<String, String> orderInfo) {
		order_info = orderInfo;
	}
	
	
}
