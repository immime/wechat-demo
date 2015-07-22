package cc.wechat.sdk.mp.rest.entity.msg.send;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 文本消息
 * @author weny
 * @datetime 2015年7月22日 下午4:46:45
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TextSendMsg extends BaseSendMsg {

	static final long serialVersionUID = -3644912302262053375L;
	
	private Text text;
	public TextSendMsg() {
	}
	
	public TextSendMsg(Text text) {
		super();
		this.text = text;
	}

	public Text getText() {
		return text;
	}

	public void setText(Text text) {
		this.text = text;
	}

}
