package cc.wechat.sdk.mp.rest.entity.msg.send;

/**
 * 素材消息
 * 
 * @author weny
 * @datetime 2015年7月22日 下午5:57:04
 */
public class MediaSendMsg extends BaseSendMsg {

	static final long serialVersionUID = -2312801991978773084L;
	/**
	 * 媒体类型
	 */
	private String mediaType;
	/**
	 * 本地路径
	 */
	private String path;

	public MediaSendMsg() {
	}

	public MediaSendMsg(String mediaType, String path) {
		super();
		this.mediaType = mediaType;
		this.path = path;
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
