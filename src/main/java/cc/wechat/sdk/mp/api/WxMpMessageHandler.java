package cc.wechat.sdk.mp.api;

import java.util.Map;

import cc.wechat.sdk.common.exception.WxErrorException;
import cc.wechat.sdk.common.session.WxSessionManager;
import cc.wechat.sdk.mp.bean.WxMpXmlMessage;
import cc.wechat.sdk.mp.bean.WxMpXmlOutMessage;

/**
 * 处理微信推送消息的处理器接口
 *
 * @author Daniel Qian
 */
public interface WxMpMessageHandler {

  /**
   * @param wxMessage
   * @param context        上下文，如果handler或interceptor之间有信息要传递，可以用这个
   * @param wxMpService
   * @param sessionManager
   * @return xml格式的消息，如果在异步规则里处理的话，可以返回null
   */
  public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                  Map<String, Object> context,
                                  WxMpService wxMpService,
                                  WxSessionManager sessionManager) throws WxErrorException;

}
