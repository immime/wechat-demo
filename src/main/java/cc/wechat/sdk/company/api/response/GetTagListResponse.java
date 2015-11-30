package cc.wechat.sdk.company.api.response;

import java.util.List;

import cc.wechat.sdk.api.response.BaseResponse;
import cc.wechat.sdk.company.api.entity.QYTag;

import com.alibaba.fastjson.annotation.JSONField;

/**
 *  Response -- 获取标签列表
 *  ====================================================================
 *  上海聚攒软件开发有限公司
 *  --------------------------------------------------------------------
 *  @author Nottyjay
 *  @version 1.0.beta
 *  @since 1.3.6
 *  ====================================================================
 */
public class GetTagListResponse extends BaseResponse {

    @JSONField(name = "taglist")
    private List<QYTag> tags;

    public List<QYTag> getTags() {
        return tags;
    }

    public void setTags(List<QYTag> tags) {
        this.tags = tags;
    }
}
