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
	private String mediaType;
	/**
	 * 本地路径
	 */
	private String path;

	public MediaOutMsg() {
	}

	public MediaOutMsg(String media_id, String mediaType, String path) {
		super();
		this.media_id = media_id;
		this.mediaType = mediaType;
		this.path = path;
	}

	public String getMedia_id() {
		return media_id;
	}

	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
