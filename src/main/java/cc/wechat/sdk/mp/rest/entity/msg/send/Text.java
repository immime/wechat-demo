package cc.wechat.sdk.mp.rest.entity.msg.send;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Text implements Serializable {

	static final long serialVersionUID = 5923848461439441633L;
	/**
	 * 文本消息内容
	 */
	private String content;

	public Text() {
	}

	public Text(String content) {
		super();
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
