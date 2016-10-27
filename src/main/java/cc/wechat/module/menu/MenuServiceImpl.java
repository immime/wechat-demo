package cc.wechat.module.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cc.wechat.data.domain.WxMenu;
import cc.wechat.module.menu.dao.MenuRepository;

@Service("menuService")
public class MenuServiceImpl implements MenuService {
	
	@Autowired
	private MenuRepository repository;

	@Transactional
	@Override
	public Long save(WxMenu entity) {
		return repository.save(entity).getId();
	}
	
	@Transactional
	@Override
	public void save(Iterable<WxMenu> entities) {
		repository.save(entities);
	}

	@Override
	public WxMenu findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public boolean exists(Long id) {
		return repository.exists(id);
	}

	@Override
	public Iterable<WxMenu> findAll() {
		return repository.findAll();
	}

	@Override
	public long count() {
		return repository.count();
	}

	@Transactional
	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

	@Transactional
	@Override
	public void deleteAll() {
		repository.deleteAll();
	}
	
}
