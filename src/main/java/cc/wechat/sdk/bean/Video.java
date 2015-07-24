package cc.wechat.sdk.bean;

import java.io.Serializable;

/**
 * 永久视频素材
 * 
 * @author weny
 * @datetime 2015年7月24日 上午11:02:04
 */
public class Video implements Serializable {

	static final long serialVersionUID = 7984831319002380664L;
	/**
	 * 视频素材的标题
	 */
	private String title;
	/**
	 * 视频素材的描述
	 */
	private String introduction;

	public Video() {
	}

	public Video(String title, String introduction) {
		super();
		this.title = title;
		this.introduction = introduction;
	}

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
