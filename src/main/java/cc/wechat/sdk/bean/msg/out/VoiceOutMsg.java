package cc.wechat.sdk.bean.msg.out;

import cc.wechat.sdk.bean.Voice;

/**
 * 语音消息
 * @author weny
 * @datetime 2015年7月24日 下午2:52:18
 */
public class VoiceOutMsg extends BaseOutMsg {

	static final long serialVersionUID = 9578877189124249L;

	private Voice voice;

	public VoiceOutMsg() {
	}

	public VoiceOutMsg(Voice voice) {
		super();
		this.voice = voice;
	}

	public Voice getVoice() {
		return voice;
	}

	public void setVoice(Voice voice) {
		this.voice = voice;
	}

}
