package cc.wechat.openapi.exception;

public class ApiStoreException extends RuntimeException {

	private static final long serialVersionUID = -5412846621182159295L;

	public ApiStoreException() {
		super();
	}

	public ApiStoreException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ApiStoreException(String message, Throwable cause) {
		super(message, cause);
	}

	public ApiStoreException(String message) {
		super(message);
	}

	public ApiStoreException(Throwable cause) {
		super(cause);
	}
	
	public ApiStoreException(String errorUrl, String message) {
		super("ApiStore未正确响应URL" + errorUrl + "的请求：{" + message + "}");
	}
	
}
