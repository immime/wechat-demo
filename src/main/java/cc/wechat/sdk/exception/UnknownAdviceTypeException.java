package cc.wechat.sdk.exception;

/**
 * 参数类型异常
 * @author weny
 * @datetime 2015年7月22日 下午6:11:32
 */
public class UnknownAdviceTypeException extends ApiException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8792223289121063101L;

	/**
	 * Create a new UnknownAdviceTypeException for the given advice object. Will
	 * create a message text that says that the object is neither a subinterface
	 * of Advice nor an Advisor.
	 * 
	 * @param advice
	 *            the advice object of unknown type
	 */
	public UnknownAdviceTypeException(Object advice) {
		super("Advice object [" + advice + "] is neither a supported subinterface of "
				+ "[org.aopalliance.aop.Advice] nor an [org.springframework.aop.Advisor]");
	}

	/**
	 * Create a new UnknownAdviceTypeException with the given message.
	 * 
	 * @param message
	 *            the message text
	 */
	public UnknownAdviceTypeException(String message) {
		super(message);
	}
}
