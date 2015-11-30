package cc.wechat.sdk.api.response;

import java.util.List;

import cc.wechat.sdk.api.entity.UserReadHour;

/**
 * @author peiyu
 */
public class GetUserReadHourResponse extends BaseResponse {

    private List<UserReadHour> list;

    public List<UserReadHour> getList() {
        return list;
    }

    public void setList(List<UserReadHour> list) {
        this.list = list;
    }
}
