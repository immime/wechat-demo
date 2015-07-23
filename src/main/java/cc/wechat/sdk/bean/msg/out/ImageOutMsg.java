package cc.wechat.sdk.bean.msg.out;

import cc.wechat.sdk.bean.Image;

/**
 * 图片消息
 * @author weny
 * @datetime 2015年7月22日 下午5:30:54
 */
public class ImageOutMsg extends BaseOutMsg {

	static final long serialVersionUID = -1274048529158267773L;
	private Image image;

	public ImageOutMsg() {
	}

	public ImageOutMsg(Image image) {
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
