package cc.wechat.sdk.message.req;

public enum ReqType {

	text(null), 
	image(null), 
	link(null), 
	location(null), 
	voice(null), 
	video(null), 
	shortvideo(null), 
	event(null),

	subscribe(event), 
	unsubscribe(event), 
	CLICK(event), 
	VIEW(event), 
	UPLOADLOCATION(event), 
	SCAN(event), 
	scancode_push(event), 
	scancode_waitmsg(event), 
	pic_sysphoto(event), 
	pic_photo_or_album(event), 
	pic_weixin(event), 
	location_select(event), 
	TEMPLATESENDJOBFINISH(event), 
	MASSSENDJOBFINISH(event);

	private final ReqType parent;

	private ReqType(ReqType parent) {
		this.parent = parent;
	}

	public ReqType getParent() {
		return parent;
	}

}
