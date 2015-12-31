package cc.wechat.data.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.apache.commons.lang3.ArrayUtils;
import org.hibernate.annotations.NaturalId;
import org.springframework.boot.actuate.autoconfigure.EndpointAutoConfiguration.GitInfo;

@Entity
public class ContextMenu extends BaseEntity implements Comparable {

	private static final long serialVersionUID = 3339126454114661937L;
	
	@Id
	@Column(nullable = false)
	private String code;
	
	@Column(nullable = false)
	private String node;
	
	@Column(nullable = false)
	private String displayName;
	
	@Column
	private String endpoint;
	
	/**父组织*/
    @ManyToOne
    @JoinColumn(name="parent_code")
    private ContextMenu parent;
    
    /**子组织*/
    @OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinColumn(name="parent_code")
    @OrderBy("node asc")
    private Set<ContextMenu> children = new HashSet<ContextMenu>();

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public ContextMenu getParent() {
		return parent;
	}

	public void setParent(ContextMenu parent) {
		this.parent = parent;
	}

	public Set<ContextMenu> getChildren() {
		return children;
	}

	public void setChildren(Set<ContextMenu> children) {
		this.children = children;
	}

	@Override
	public int compareTo(Object o) {
		ContextMenu m = (ContextMenu) o;
		String thisCode = this.getCode();
		String targetCode = m.getCode();
			
		if(thisCode.equals(targetCode)) {
			return 0;
		} 
		
		String[] targetArr = targetCode.split("_");
		String[] thisArr = thisCode.split("_");
		
		if(targetArr.length > thisArr.length) {
			return 1;
		}
		if (targetArr.length < thisArr.length) {
			return -1;
		}
		if (targetArr.length == thisArr.length) {
			int result = -1;
			for (int i = 0; i < thisArr.length; i++) {
				int targetNode = Integer.valueOf(targetArr[i]);
				int thisNode = Integer.valueOf(thisArr[i]);
				if(targetNode > thisNode) {
					result = 1;
				}
			}
			return result;
		}
		
		return -1;
	}
	
	//////////////////////////////////////// 
	// test code
	////////////////////////////////////////
//	public static void main(String[] args) {
//		ContextMenu m1 = new ContextMenu();
//		m1.setCode("0_1_2_");
//		ContextMenu m2 = new ContextMenu();
//		m2.setCode("0_1_2_");
//		
//		System.err.println(m1.compareTo(m2));
//		
////		String[] arr = "".split("_");
////		System.err.println(arr.toString());
//	}
	
}
