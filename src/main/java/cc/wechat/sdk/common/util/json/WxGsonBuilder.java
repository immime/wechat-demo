package cc.wechat.sdk.common.util.json;

import cc.wechat.sdk.common.bean.WxAccessToken;
import cc.wechat.sdk.common.bean.WxMenu;
import cc.wechat.sdk.common.bean.result.WxError;
import cc.wechat.sdk.common.bean.result.WxMediaUploadResult;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class WxGsonBuilder {

	public static final GsonBuilder INSTANCE = new GsonBuilder();

	static {
		INSTANCE.disableHtmlEscaping();
		INSTANCE.registerTypeAdapter(WxAccessToken.class, new WxAccessTokenAdapter());
		INSTANCE.registerTypeAdapter(WxError.class, new WxErrorAdapter());
		INSTANCE.registerTypeAdapter(WxMenu.class, new WxMenuGsonAdapter());
		INSTANCE.registerTypeAdapter(WxMediaUploadResult.class, new WxMediaUploadResultAdapter());
	}

	public static Gson create() {
		return INSTANCE.create();
	}

}
