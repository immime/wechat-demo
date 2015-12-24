package cc.wechat.sdk.company.message.resp;

import java.util.ArrayList;
import java.util.List;

import cc.wechat.sdk.company.message.QYArticleMsg;
import cc.wechat.sdk.message.ArticleMsg;
import cc.wechat.sdk.message.RespType;
import cc.wechat.sdk.message.util.MessageBuilder;

/**
 *  微信企业号被动响应事件新闻消息
 *  ====================================================================
 *  上海聚攒软件开发有限公司
 *  --------------------------------------------------------------------
 *  @author Nottyjay
 *  @version 1.0.beta
 *  @since 1.3.6
 *  ====================================================================
 */
public class QYNewsRespMsg extends QYBaseRespMsg {

    private static final int WX_MAX_SIZE = 10;
    private              int maxSize     = WX_MAX_SIZE;

    private List<QYArticleMsg> articles;

    public QYNewsRespMsg() {
        articles = new ArrayList<QYArticleMsg>(maxSize);
    }

    public QYNewsRespMsg(int maxSize) {
        setMaxSize(maxSize);
        articles = new ArrayList<QYArticleMsg>(maxSize);
    }

    public QYNewsRespMsg(List<QYArticleMsg> articles) {
        this.articles = articles;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        if (maxSize < WX_MAX_SIZE && maxSize >= 1) {
            this.maxSize = maxSize;
        }
        if (articles != null && articles.size() > this.maxSize) {
            articles = articles.subList(0, this.maxSize);
        }
    }

    public List<QYArticleMsg> getArticles() {
        return articles;
    }

    public void setArticles(List<QYArticleMsg> articles) {
        if (articles.size() > this.maxSize) {
            this.articles = articles.subList(0, this.maxSize);
        } else {
            this.articles = articles;
        }
    }

    public void add(String title) {
        add(title, null, null, null);
    }

    public void add(String title, String url) {
        add(title, null, null, url);
    }

    public void add(String title, String picUrl, String url) {
        add(title, null, picUrl, url);
    }

    public void add(String title, String description, String picUrl, String url) {
        add(new QYArticleMsg(title, description, picUrl, url));
    }

    public void add(QYArticleMsg article){
        if (this.articles.size() < maxSize) {
            this.articles.add(article);
        }
    }

    @Override
    public String toXml() {
        MessageBuilder mb = new MessageBuilder(super.toXml());
        mb.addData("MsgType", RespType.NEWS);
        mb.addTag("ArticleCount", String.valueOf(articles.size()));
        mb.append("<Articles>\n");
        for (ArticleMsg article : articles) {
            mb.append(article.toXml());
        }
        mb.append("</Articles>\n");
        mb.surroundWith("xml");
        return mb.toString();
    }
}
