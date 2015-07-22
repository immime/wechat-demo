package cc.wechat.sdk.mp.rest.entity.msg.send;

/**
 * 图片消息
 * @author weny
 * @datetime 2015年7月22日 下午5:30:54
 */
public class ImageSendMsg extends BaseSendMsg {

	static final long serialVersionUID = -1274048529158267773L;
	private Image image;

	public ImageSendMsg() {
	}

	public ImageSendMsg(Image image) {
		super();
		this.image = image;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
}
