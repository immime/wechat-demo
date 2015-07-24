package cc.wechat.sdk.bean.msg.in;



/**
 * 封装上传素材时来自微信官方的http响应
 * 
 * @author weny
 * @datetime 2015年7月23日 上午10:11:23
 */
public class MediaInMsg extends BaseInMsg {
	static final long serialVersionUID = 1313183218065381724L;
	private String type;
	private String media_id;
	private String created_at;
	private String localPath;

	public MediaInMsg() {
	}

	public MediaInMsg(String type, String media_id, String created_at, String localPath) {
		super();
		this.type = type;
		this.media_id = media_id;
		this.created_at = created_at;
		this.localPath = localPath;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMedia_id() {
		return media_id;
	}

	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getLocalPath() {
		return localPath;
	}

	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}

}
