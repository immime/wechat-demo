package cc.wechat.module.joke.vo;

public class JokeVO {
	/**
	 * 时间
	 */
	private String ct;
	/**
	 * 类型
	 */
	private String type;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 正文
	 */
	private String text;
	
	public String getCt() {
		return ct;
	}
	public void setCt(String ct) {
		this.ct = ct;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
}
