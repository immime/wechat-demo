package cc.wechat.sdk.exception;

/**
 * Http请求异常
 * @author weny
 * @datetime 2015年7月23日 上午11:29:03
 */
public class ConnectException extends ApiException {

	static final long serialVersionUID = 4214201355814568536L;
	
	public ConnectException() {
		super();
	}
	
	public ConnectException(String message) {
		super(message);
	}

	public ConnectException(String message, Throwable cause) {
		super(message, cause);
	}

	public ConnectException(Throwable cause) {
		super(cause);
	}
	
	

}
