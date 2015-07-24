package cc.wechat.sdk.bean.msg.out;

import cc.wechat.sdk.bean.Text;

/**
 * 文本消息
 * @author weny
 * @datetime 2015年7月22日 下午4:46:45
 */
public class TextOutMsg extends BaseOutMsg {

	static final long serialVersionUID = -3644912302262053375L;
	
	private Text text;
	public TextOutMsg() {
	}
	
	public TextOutMsg(Text text) {
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
