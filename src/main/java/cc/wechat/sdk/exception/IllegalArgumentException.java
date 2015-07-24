package cc.wechat.sdk.exception;

/**
 * 参数异常
 * @author weny
 * @datetime 2015年7月24日 下午12:51:20
 */
public class IllegalArgumentException extends ApiException {
	
	static final long serialVersionUID = -365568746787111827L;

	/**
	 * Constructs an <code>IllegalArgumentException</code> with no detail
	 * message.
	 */
	public IllegalArgumentException() {
		super();
	}

	/**
	 * Constructs an <code>IllegalArgumentException</code> with the specified
	 * detail message.
	 *
	 * @param s
	 *            the detail message.
	 */
	public IllegalArgumentException(String s) {
		super(s);
	}

	/**
	 * Constructs a new exception with the specified detail message and cause.
	 *
	 * <p>
	 * Note that the detail message associated with <code>cause</code> is
	 * <i>not</i> automatically incorporated in this exception's detail message.
	 *
	 * @param message
	 *            the detail message (which is saved for later retrieval by the
	 *            {@link Throwable#getMessage()} method).
	 * @param cause
	 *            the cause (which is saved for later retrieval by the
	 *            {@link Throwable#getCause()} method). (A <tt>null</tt> value
	 *            is permitted, and indicates that the cause is nonexistent or
	 *            unknown.)
	 * @since 1.5
	 */
	public IllegalArgumentException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructs a new exception with the specified cause and a detail message
	 * of <tt>(cause==null ? null : cause.toString())</tt> (which typically
	 * contains the class and detail message of <tt>cause</tt>). This
	 * constructor is useful for exceptions that are little more than wrappers
	 * for other throwables (for example,
	 * {@link java.security.PrivilegedActionException}).
	 *
	 * @param cause
	 *            the cause (which is saved for later retrieval by the
	 *            {@link Throwable#getCause()} method). (A <tt>null</tt> value
	 *            is permitted, and indicates that the cause is nonexistent or
	 *            unknown.)
	 * @since 1.5
	 */
	public IllegalArgumentException(Throwable cause) {
		super(cause);
	}

}
