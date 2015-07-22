package cc.wechat.config;

import org.xml.sax.helpers.DefaultHandler;

import cc.wechat.sdk.common.api.WxConsts;
import cc.wechat.sdk.mp.api.WxMpConfigStorage;
import cc.wechat.sdk.mp.api.WxMpInMemoryConfigStorage;
import cc.wechat.sdk.mp.api.WxMpMessageHandler;
import cc.wechat.sdk.mp.api.WxMpMessageRouter;
import cc.wechat.sdk.mp.api.WxMpService;
import cc.wechat.sdk.mp.api.WxMpServiceImpl;

/**
 * 微信配置管理类
 * 
 * @author weny
 * @datetime 2015年7月21日 上午10:19:59
 * @version V2.0
 */
public class WeixinManager {

	/**
	 * 微信配置
	 */
	private static final WxMpInMemoryConfigStorage config = new WxMpInMemoryConfigStorage();
	/**
	 * 微信service
	 */
	private static WxMpService wxMpService;
	/**
	 * 消息路由
	 */
	private static WxMpMessageRouter wxMpMessageRouter;

	static {
		config.setAppId("wxe6626fc25736c77e"); // 设置微信公众号的appid
		config.setSecret("c5ea13a94c08a1ed07fc4eaeb6ca913b"); // 设置微信公众号的app
		config.setToken("myqiqi"); // 设置微信公众号的token
		config.setAesKey("DvILSt6R9ciXQCP9rA7HusoEzEhPTKPUXKtYjCIiZxn"); // 设置微信公众号的EncodingAESKey

		wxMpService = new WxMpServiceImpl();
		wxMpService.setWxMpConfigStorage(config);
	}

	/**
	 * 获取微信配置
	 * 
	 * @author weny
	 * @date 2015年7月21日 上午10:19:45
	 * @return
	 */
	public static WxMpConfigStorage getConfig() {
		return config;
	}

	/**
	 * 获取WxMpService
	 * 
	 * @author weny
	 * @date 2015年7月21日 下午12:09:47
	 * @return
	 */
	public static WxMpService getService() {
		return wxMpService;
	}

	/**
	 * 获取消息路由
	 * 
	 * @author weny
	 * @date 2015年7月21日 下午12:11:38
	 * @return
	 */
	public static WxMpMessageRouter getMsgRouter() {
		return wxMpMessageRouter;
	}

}
