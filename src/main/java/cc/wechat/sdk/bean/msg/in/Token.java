package cc.wechat.sdk.bean.msg.in;


/**
 * 微信服务器返回的token
 * @author weny
 * @datetime 2015年7月24日 下午12:09:41
 */
public class Token extends BaseInMsg{
	
	static final long serialVersionUID = -7969371422871383262L;
	
	private String access_token;
	private String expires_in;

	public Token() {
	}

	public Token(String access_token, String expires_in) {
		super();
		this.access_token = access_token;
		this.expires_in = expires_in;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}

}
