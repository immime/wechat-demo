package cc.wechat.sdk.bean;

import java.io.Serializable;

/**
 * 语音消息
 * @author weny
 * @datetime 2015年7月24日 下午2:50:13
 */
public class Voice implements Serializable {
	
	static final long serialVersionUID = -6967655981575805812L;
	
	private String media_id;

	public Voice() {
	}

	public Voice(String media_id) {
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
