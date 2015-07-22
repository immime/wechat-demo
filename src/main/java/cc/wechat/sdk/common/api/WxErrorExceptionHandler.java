package cc.wechat.sdk.common.api;

import cc.wechat.sdk.common.exception.WxErrorException;

/**
 * WxErrorException处理器
 */
public interface WxErrorExceptionHandler {

  public void handle(WxErrorException e);

}
