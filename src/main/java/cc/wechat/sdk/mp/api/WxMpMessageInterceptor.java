package cc.wechat.sdk.mp.api;

import java.util.Map;

import cc.wechat.sdk.common.exception.WxErrorException;
import cc.wechat.sdk.common.session.WxSessionManager;
import cc.wechat.sdk.mp.bean.WxMpXmlMessage;

/**
 * 微信消息拦截器，可以用来做验证
 *
 * @author Daniel Qian
 */
public interface WxMpMessageInterceptor {

  /**
   * 拦截微信消息
   *
   * @param wxMessage
   * @param context        上下文，如果handler或interceptor之间有信息要传递，可以用这个
   * @param wxMpService
   * @param sessionManager
   * @return true代表OK，false代表不OK
   */
  public boolean intercept(WxMpXmlMessage wxMessage,
                            Map<String, Object> context,
                            WxMpService wxMpService,
                            WxSessionManager sessionManager) throws WxErrorException;

}
