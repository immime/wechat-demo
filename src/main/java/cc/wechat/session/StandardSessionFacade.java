package cc.wechat.session;

import java.util.Enumeration;

import org.springframework.stereotype.Component;

@Component
public class StandardSessionFacade implements IWechatSession {

  /**
   * Wrapped session object.
   */
  private IWechatSession session = null;
  
  public StandardSessionFacade() {
  }

  public StandardSessionFacade(StandardSession session) {
    this.session = session;
  }

  public IInternalSession getInternalSession() {
    return (IInternalSession) session;
  }

  @Override
  public Object getAttribute(String name) {
    return session.getAttribute(name);
  }

  @Override
  public Enumeration<String> getAttributeNames() {
    return session.getAttributeNames();
  }

  @Override
  public void setAttribute(String name, Object value) {
    session.setAttribute(name, value);
  }

  @Override
  public void removeAttribute(String name) {
    session.removeAttribute(name);
  }

  @Override
  public void invalidate() {
    session.invalidate();
  }

}
