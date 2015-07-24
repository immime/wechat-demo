package cc.wechat.sdk.bean.msg.out;

/**
 * 临时素材消息
 * 
 * @author weny
 * @datetime 2015年7月22日 下午5:57:04
 */
public class MediaOutMsg extends BaseOutMsg {

	static final long serialVersionUID = -2312801991978773084L;

	/**
	 * 媒体文件ID
	 */
	private String media_id;
	/**
	 * 媒体类型
	 */
	private String type;
	/**
	 * 本地路径
	 */
	private String localPath;

	public MediaOutMsg() {
	}

	public MediaOutMsg(String media_id, String type, String localPath) {
		super();
		this.media_id = media_id;
		this.type = type;
		this.localPath = localPath;
	}

	public String getMedia_id() {
		return media_id;
	}

	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLocalPath() {
		return localPath;
	}

	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}

}
