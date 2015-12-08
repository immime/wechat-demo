package cc.wechat.sdk.message.req;


public final class TextReqMsg extends BaseReqMsg {

    private String content;

    public TextReqMsg(String content) {
        super();
        this.content = content;
        setMsgType(ReqType.text.name());
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "TextReqMsg [content=" + content + ", toUserName=" + toUserName
                + ", fromUserName=" + fromUserName + ", createTime="
                + createTime + ", msgType=" + msgType + ", msgId=" + msgId
                + "]";
    }

}
