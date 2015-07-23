package cc.wechat.sdk.bean;

import java.io.Serializable;

public class Image implements Serializable {

	static final long serialVersionUID = -3810385796803106178L;
	private String media_id;

	public Image() {
	}

	public Image(String media_id) {
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
