package cc.wechat.sdk.mp.util.json;

import cc.wechat.sdk.mp.bean.WxMpCustomMessage;
import cc.wechat.sdk.mp.bean.WxMpGroup;
import cc.wechat.sdk.mp.bean.WxMpMassGroupMessage;
import cc.wechat.sdk.mp.bean.WxMpMassNews;
import cc.wechat.sdk.mp.bean.WxMpMassOpenIdsMessage;
import cc.wechat.sdk.mp.bean.WxMpMassVideo;
import cc.wechat.sdk.mp.bean.WxMpTemplateMessage;
import cc.wechat.sdk.mp.bean.result.WxMpMassSendResult;
import cc.wechat.sdk.mp.bean.result.WxMpMassUploadResult;
import cc.wechat.sdk.mp.bean.result.WxMpOAuth2AccessToken;
import cc.wechat.sdk.mp.bean.result.WxMpQrCodeTicket;
import cc.wechat.sdk.mp.bean.result.WxMpSemanticQueryResult;
import cc.wechat.sdk.mp.bean.result.WxMpUser;
import cc.wechat.sdk.mp.bean.result.WxMpUserCumulate;
import cc.wechat.sdk.mp.bean.result.WxMpUserList;
import cc.wechat.sdk.mp.bean.result.WxMpUserSummary;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class WxMpGsonBuilder {

  public static final GsonBuilder INSTANCE = new GsonBuilder();
  
  static {
    INSTANCE.disableHtmlEscaping();
    INSTANCE.registerTypeAdapter(WxMpCustomMessage.class, new WxMpCustomMessageGsonAdapter());
    INSTANCE.registerTypeAdapter(WxMpMassNews.class, new WxMpMassNewsGsonAdapter());
    INSTANCE.registerTypeAdapter(WxMpMassGroupMessage.class, new WxMpMassGroupMessageGsonAdapter());
    INSTANCE.registerTypeAdapter(WxMpMassOpenIdsMessage.class, new WxMpMassOpenIdsMessageGsonAdapter());
    INSTANCE.registerTypeAdapter(WxMpGroup.class, new WxMpGroupGsonAdapter());
    INSTANCE.registerTypeAdapter(WxMpUser.class, new WxMpUserGsonAdapter());
    INSTANCE.registerTypeAdapter(WxMpUserList.class, new WxUserListGsonAdapter());
    INSTANCE.registerTypeAdapter(WxMpMassVideo.class, new WxMpMassVideoAdapter());
    INSTANCE.registerTypeAdapter(WxMpMassSendResult.class, new WxMpMassSendResultAdapter());
    INSTANCE.registerTypeAdapter(WxMpMassUploadResult.class, new WxMpMassUploadResultAdapter());
    INSTANCE.registerTypeAdapter(WxMpQrCodeTicket.class, new WxQrCodeTicketAdapter());
    INSTANCE.registerTypeAdapter(WxMpTemplateMessage.class, new WxMpTemplateMessageGsonAdapter());
    INSTANCE.registerTypeAdapter(WxMpSemanticQueryResult.class, new WxMpSemanticQueryResultAdapter());
    INSTANCE.registerTypeAdapter(WxMpOAuth2AccessToken.class, new WxMpOAuth2AccessTokenAdapter());
    INSTANCE.registerTypeAdapter(WxMpUserSummary.class, new WxMpUserSummaryGsonAdapter());
    INSTANCE.registerTypeAdapter(WxMpUserCumulate.class, new WxMpUserCumulateGsonAdapter());
  }
  
  public static Gson create() {
    return INSTANCE.create();
  }
  
}
