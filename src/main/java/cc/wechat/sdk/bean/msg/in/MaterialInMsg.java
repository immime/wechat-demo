package cc.wechat.sdk.bean.msg.in;

import java.util.List;

import cc.wechat.sdk.bean.Article;

/**
 * 永久素材
 * 
 * @author weny
 * @datetime 2015年7月23日 下午5:16:48
 */
public class MaterialInMsg extends BaseInMsg {

	static final long serialVersionUID = -4642254378930820814L;

	/**
	 * 图文素材集合，若新增的是多图文素材，articles.size() > 1
	 */
	private List<Article> articles;

	public MaterialInMsg() {
	}

	public MaterialInMsg(List<Article> articles) {
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
