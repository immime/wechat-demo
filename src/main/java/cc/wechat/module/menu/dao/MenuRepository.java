package cc.wechat.module.menu.dao;

import org.springframework.data.repository.CrudRepository;

import cc.wechat.data.domain.WxMenu;

public interface MenuRepository extends CrudRepository<WxMenu, Long> {

	@Override
	<S extends WxMenu> S save(S entity);
	
	@Override
	<S extends WxMenu> Iterable<S> save(Iterable<S> entities);

	@Override
	WxMenu findOne(Long id);

	@Override
	boolean exists(Long id);

	@Override
	Iterable<WxMenu> findAll();

	@Override
	long count();

	@Override
	void delete(Long id);

	@Override
	void deleteAll();

}
