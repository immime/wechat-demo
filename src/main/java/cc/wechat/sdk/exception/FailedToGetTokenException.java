package cc.wechat.sdk.exception;

public class FailedToGetTokenException extends ApiException {

	static final long serialVersionUID = 1465295989379846081L;
	
    /**
     * Constructs an <code>ApiGetTokenFailedException</code> without a
     * detail message.
     */
    public FailedToGetTokenException() {
        super();
    }

    /**
     * Constructs an <code>ApiGetTokenFailedException</code> with a detail message.
     *
     * @param   s   the detail message.
     */
    public FailedToGetTokenException(String s) {
        super(s);
    }

}
