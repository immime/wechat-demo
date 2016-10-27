package cc.wechat.module.menu;

import cc.wechat.data.domain.WxMenu;

public interface MenuService {
	/**
	 * 保存实体
	 * @param entity
	 * @return 新增实体的id
	 */
	Long save(WxMenu entity);
	
	/**
	 * 批量保存
	 * @param entities
	 */
	void save(Iterable<WxMenu> entities);

	/**
	 * 查找一条记录
	 * @param id
	 * @return
	 */
	WxMenu findOne(Long id);

	/**
	 * 检查是否已存在
	 * @param id
	 * @return
	 */
	boolean exists(Long id);

	/**
	 * 获取所有记录
	 * @return
	 */
	Iterable<WxMenu> findAll();

	/**
	 * 返回记录数
	 * @return
	 */
	long count();

	/**
	 * 删除一条记录
	 * @param id
	 */
	void delete(Long id);

	/**
	 * 删除所有记录
	 */
	void deleteAll();
}
