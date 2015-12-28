package cc.wechat.sdk.message.req;

public class BaseReq {

    String toUserName;
    String fromUserName;
    long   createTime;
    ReqType msgType;

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public ReqType getMsgType() {
        return msgType;
    }

    public void setMsgType(ReqType msgType) {
        this.msgType = msgType;
    }

}
