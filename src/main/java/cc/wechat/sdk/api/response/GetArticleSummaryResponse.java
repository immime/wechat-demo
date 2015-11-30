package cc.wechat.sdk.api.response;

import java.util.List;

import cc.wechat.sdk.api.entity.ArticleSummary;

/**
 * @author peiyu
 */
public class GetArticleSummaryResponse extends BaseResponse {

    private List<ArticleSummary> list;

    public List<ArticleSummary> getList() {
        return list;
    }

    public void setList(List<ArticleSummary> list) {
        this.list = list;
    }
}
