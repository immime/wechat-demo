package cc.wechat.sdk.mp.bean.custombuilder;

import cc.wechat.sdk.common.api.WxConsts;
import cc.wechat.sdk.mp.bean.WxMpCustomMessage;

/**
 * 语音消息builder
 * <pre>
 * 用法: WxMpCustomMessage m = WxMpCustomMessage.VOICE().mediaId(...).toUser(...).build();
 * </pre>
 * @author chanjarster
 *
 */
public final class VoiceBuilder extends BaseBuilder<VoiceBuilder> {
  private String mediaId;

  public VoiceBuilder() {
    this.msgType = WxConsts.CUSTOM_MSG_VOICE;
  }

  public VoiceBuilder mediaId(String media_id) {
    this.mediaId = media_id;
    return this;
  }

  public WxMpCustomMessage build() {
    WxMpCustomMessage m = super.build();
    m.setMediaId(this.mediaId);
    return m;
  }
}
