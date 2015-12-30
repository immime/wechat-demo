package cc.wechat.sdk.message.req;

import java.util.List;
import java.util.Map;

public class SendPicsInfoEvent extends BaseReqEvent {
	
	private String eventKey;
	private Integer count;
	private List<Map<String, Object>> picList;
	
	public SendPicsInfoEvent(String eventKey, Integer count, List<Map<String, Object>> picList) {
		super();
		this.eventKey = eventKey;
		this.count = count;
		this.picList = picList;
	}

	public String getEventKey() {
		return eventKey;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public List<Map<String, Object>> getPicList() {
		return picList;
	}

	public void setPicList(List<Map<String, Object>> picList) {
		this.picList = picList;
	}

    @Override
    public String toString() {
        return "SendPicsInfoEvent{" +
                "eventKey='" + eventKey + '\'' +
                ", count=" + count +
                ", picList=" + picList +
                '}';
    }
}
