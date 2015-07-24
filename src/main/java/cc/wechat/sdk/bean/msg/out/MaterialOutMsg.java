package cc.wechat.sdk.bean.msg.out;

/**
 * 其他类型永久素材
 * 
 * @author weny
 * @datetime 2015年7月23日 下午4:31:45
 */
public class MaterialOutMsg extends BaseOutMsg {

	static final long serialVersionUID = 1790522136609758460L;

	/**
	 * 媒体文件ID
	 */
	private String media_id;
	/**
	 * 媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
	 */
	private String type;
	/**
	 * form-data中媒体文件标识，有filename、filelength、content-type等信息
	 */
	private String media;
	/**
	 * 上传文件的本地路径
	 */
	private String localPath;
	/**
	 * 包含视频的描述信息
	 */
	private Description description;

	public MaterialOutMsg() {
	}

	public MaterialOutMsg(String media_id, String type, String media, String localPath, Description description) {
		super();
		this.media_id = media_id;
		this.type = type;
		this.media = media;
		this.localPath = localPath;
		this.description = description;
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

	public String getMedia() {
		return media;
	}

	public void setMedia(String media) {
		this.media = media;
	}

	public String getLocalPath() {
		return localPath;
	}

	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}

	public Description getDescription() {
		return description;
	}

	public void setDescription(Description description) {
		this.description = description;
	}

	/**
	 * 永久素材-视频描述
	 * 
	 * @author weny
	 * @datetime 2015年7月24日 下午12:36:13
	 */
	public class Description {
		/**
		 * 视频素材的标题
		 */
		private String title;
		/**
		 * 视频素材的描述
		 */
		private String introduction;

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getIntroduction() {
			return introduction;
		}

		public void setIntroduction(String introduction) {
			this.introduction = introduction;
		}
	}

}
