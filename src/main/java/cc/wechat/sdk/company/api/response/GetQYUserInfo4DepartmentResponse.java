package cc.wechat.sdk.company.api.response;
/**
 * Created by Nottyjay on 2015/6/11.
 */

import java.util.List;

import cc.wechat.sdk.api.response.BaseResponse;
import cc.wechat.sdk.company.api.entity.QYUser;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * ====================================================================
 * 上海聚攒软件开发有限公司
 * --------------------------------------------------------------------
 *
 * @author Nottyjay
 * @version 1.0.beta
 *          ====================================================================
 */
public class GetQYUserInfo4DepartmentResponse extends BaseResponse {

    @JSONField(name = "userlist")
    public List<QYUser> userList;

    public List<QYUser> getUserList() {
        return userList;
    }

    public void setUserList(List<QYUser> userList) {
        this.userList = userList;
    }
}
