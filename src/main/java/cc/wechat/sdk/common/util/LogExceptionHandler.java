package cc.wechat.sdk.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cc.wechat.sdk.common.api.WxErrorExceptionHandler;
import cc.wechat.sdk.common.exception.WxErrorException;


public class LogExceptionHandler implements WxErrorExceptionHandler {

  private Logger log = LoggerFactory.getLogger(WxErrorExceptionHandler.class);

  @Override
  public void handle(WxErrorException e) {

    log.error("Error happens", e);

  }

}
