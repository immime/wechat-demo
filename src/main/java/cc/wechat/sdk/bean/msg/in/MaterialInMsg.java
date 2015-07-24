package cc.wechat.sdk.bean.msg.in;


/**
 * 其他类型永久素材
 * @author weny
 * @datetime 2015年7月24日 上午10:58:11
 */
public class MaterialInMsg extends BaseInMsg {
	
	static final long serialVersionUID = -4159680046376435026L;
	
	/**
	 * 新增的永久素材的media_id
	 */
	private String media_id;
	/**
	 * 新增的图片素材的图片URL（仅新增图片素材时会返回该字段）
	 */
	private String url;
	/**
	 * 获取到的永久素材存储的本地路径
	 */
	private String localPath;

	public MaterialInMsg() {
	}

	public MaterialInMsg(String media_id, String url, String localPath) {
		super();
		this.media_id = media_id;
		this.url = url;
		this.localPath = localPath;
	}

	public String getMedia_id() {
		return media_id;
	}

	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLocalPath() {
		return localPath;
	}

	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}

}
