package cc.wechat.sdk.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 微信服务器ip列表
 * @author weny
 * @datetime 2015年7月23日 下午5:09:45
 */
public class IpList implements Serializable {

	static final long serialVersionUID = 711915568862705602L;
	
	private List<String> ip_list;
	
	public IpList() {
	}

	public IpList(List<String> ip_list) {
		super();
		this.ip_list = ip_list;
	}

	public List<String> getIp_list() {
		return ip_list;
	}

	public void setIp_list(List<String> ip_list) {
		this.ip_list = ip_list;
	}

}
