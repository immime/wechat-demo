package cc.wechat.sdk.message.req;


public class BaseReqEvent extends BaseReq {

    private String event;

    public BaseReqEvent() {
        setMsgType(ReqType.event);
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

}
