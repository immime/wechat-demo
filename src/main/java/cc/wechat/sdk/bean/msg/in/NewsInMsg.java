package cc.wechat.sdk.bean.msg.in;


/**
 * 永久图文素材
 * @author weny
 * @datetime 2015年7月24日 上午10:51:59
 */
public class NewsInMsg extends BaseInMsg {
	
	static final long serialVersionUID = 4214592603246870429L;
	
	/**
	 * 返回的即为新增的图文消息素材的media_id
	 */
	private String media_id;
	
	public NewsInMsg() {
	}

	public NewsInMsg(String media_id) {
		super();
		this.media_id = media_id;
	}

	public String getMedia_id() {
		return media_id;
	}

	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}
	
}
