package cc.wechat.sdk.mp.rest.exception;

public class ApiGetTokenFailedException extends ApiException {

	static final long serialVersionUID = 1465295989379846081L;
	
    /**
     * Constructs an <code>ApiGetTokenFailedException</code> without a
     * detail message.
     */
    public ApiGetTokenFailedException() {
        super();
    }

    /**
     * Constructs an <code>ApiGetTokenFailedException</code> with a detail message.
     *
     * @param   s   the detail message.
     */
    public ApiGetTokenFailedException(String s) {
        super(s);
    }

}
