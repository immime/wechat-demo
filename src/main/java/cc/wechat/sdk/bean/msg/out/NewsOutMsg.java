package cc.wechat.sdk.bean.msg.out;

import java.util.List;

import cc.wechat.sdk.bean.Article;
import cc.wechat.sdk.bean.msg.in.BaseInMsg;

/**
 * 永久图文素材
 * 
 * @author weny
 * @datetime 2015年7月23日 下午5:16:48
 */
public class NewsOutMsg extends BaseInMsg {

	static final long serialVersionUID = -4642254378930820814L;

	/**
	 * 图文素材集合，若新增的是多图文素材，articles.size() > 1
	 */
	private List<Article> articles;

	public NewsOutMsg() {
	}

	public NewsOutMsg(List<Article> articles) {
		super();
		this.articles = articles;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
}
