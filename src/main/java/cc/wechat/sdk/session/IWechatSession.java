package cc.wechat.sdk.session;

import java.util.Enumeration;

public interface IWechatSession {

  public Object getAttribute(String name);

  public Enumeration<String> getAttributeNames();

  public void setAttribute(String name, Object value);

  public void removeAttribute(String name);

  public void invalidate();

}
