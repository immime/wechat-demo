package cc.wechat.data.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import cc.wechat.sdk.api.enums.MenuType;

@Entity
@Table(name="wx_menu")
public class WxMenu {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
    /**
     * 菜单上显示的文字
     */
    @Column(nullable = false)
    private String name;

    /**
     * 菜单key，当MenuType值为CLICK时用于区别菜单
     */
    @Column(nullable = false)
    private String menuKey;

    /**
     * 菜单跳转的URL，当MenuType值为VIEW时用
     */
    @Column
    private String url;

    /**
     * 菜单显示的永久素材的MaterialID,当MenuType值为media_id和view_limited时必需
     */
    @JSONField(name = "media_id")
    @Column
    private String mediaId;
    
    @JSONField(name = "order_num")
    @Column
    private String orderNum;
    
	/**
     * 菜单类别
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private MenuType type;
    
    @ManyToOne(optional = true)
    @JoinColumn(name = "parent_id", referencedColumnName = "id") 
    private WxMenu parent;

    /**
     * 二级菜单列表，每个一级菜单下最多5个
     */
    @JSONField(name = "sub_button")
    @OneToMany(
    		fetch=FetchType.EAGER,
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    @JoinColumn(name = "parent_id")
    @OrderBy("order_num ASC")
    private List<WxMenu> children = new ArrayList<WxMenu>();

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the menuKey
	 */
	public String getMenuKey() {
		return menuKey;
	}

	/**
	 * @param menuKey the menuKey to set
	 */
	public void setMenuKey(String menuKey) {
		this.menuKey = menuKey;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the mediaId
	 */
	public String getMediaId() {
		return mediaId;
	}

	/**
	 * @param mediaId the mediaId to set
	 */
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	/**
	 * @return the orderNum
	 */
	public String getOrderNum() {
		return orderNum;
	}

	/**
	 * @param orderNum the orderNum to set
	 */
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	/**
	 * @return the type
	 */
	public MenuType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(MenuType type) {
		this.type = type;
	}

	/**
	 * @return the parent
	 */
	public WxMenu getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(WxMenu parent) {
		this.parent = parent;
	}

	/**
	 * @return the subMenu
	 */
	@JsonManagedReference
	public List<WxMenu> getSubMenu() {
		return children;
	}

	/**
	 * @param subMenu the subMenu to set
	 */
	public void setSubMenu(List<WxMenu> subMenu) {
		this.children = subMenu;
	}
    
    
}
