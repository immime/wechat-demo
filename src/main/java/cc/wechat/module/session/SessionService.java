package cc.wechat.module.session;


/**
 * Service服务
 * @author weny
 * @datetime 2015年11月23日 上午10:41:32
 */
public interface SessionService {
	
	void put(String key, Object value);
	
	Object get(String key);
}