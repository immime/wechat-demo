package cc.wechat.data.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.alibaba.fastjson.annotation.JSONField;

@Entity
public class Joke extends BaseEntity {

	private static final long serialVersionUID = -3924824580494261604L;
	
	@Id
	@GeneratedValue
	private String id;
	/**
	 * 时间
	 */
	@JSONField(format="yyyy-MM-dd HH:mm:sss")
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date ct;
	/**
	 * 类型
	 */
	@Column(nullable = false)
	private String type;
	/**
	 * 标题
	 */
	@Column(nullable = false)
	private String title;
	/**
	 * 正文
	 */
	@Column(nullable = false, length=3000)
	private String text;
	
	public String getId() {
		return id;
	}
	public void setId(String	 id) {
		this.id = id;
	}
	public Date getCt() {
		return ct;
	}
	public void setCt(Date ct) {
		this.ct = ct;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

}
