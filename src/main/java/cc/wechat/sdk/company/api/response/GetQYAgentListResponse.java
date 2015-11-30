package cc.wechat.sdk.company.api.response;

import java.util.List;

import cc.wechat.sdk.api.response.BaseResponse;
import cc.wechat.sdk.company.api.entity.QYAgent;

import com.alibaba.fastjson.annotation.JSONField;

/**
 *  
 *  ====================================================================
 *  上海聚攒软件开发有限公司
 *  --------------------------------------------------------------------
 *  @author Nottyjay
 *  @version 1.0.beta
 *  ====================================================================
 */
public class GetQYAgentListResponse extends BaseResponse {

    @JSONField(name = "agentlist")
    public List<QYAgent> agentList;

    public List<QYAgent> getAgentList() {
        return agentList;
    }

    public void setAgentList(List<QYAgent> agentList) {
        this.agentList = agentList;
    }
}
