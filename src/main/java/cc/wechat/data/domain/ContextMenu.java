package cc.wechat.data.domain;

import java.util.HashSet;
import java.util.Set;

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

import org.hibernate.annotations.NaturalId;

@Entity
public class ContextMenu extends BaseEntity {

	private static final long serialVersionUID = 3339126454114661937L;
	
	@Id
	@Column(nullable = false)
	private String code;
	
	@Column(nullable = false)
	private String displayName;
	
	/**父组织*/
    @ManyToOne
    @JoinColumn(name="parent_code")
    private ContextMenu parent;
    
    /**子组织*/
    @OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    @JoinColumn(name="parent_code")
    @OrderBy("code asc")
    private Set<ContextMenu> children = new HashSet<ContextMenu>();

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
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
	
}
