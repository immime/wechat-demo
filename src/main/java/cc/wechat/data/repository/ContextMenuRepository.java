package cc.wechat.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import cc.wechat.data.domain.ContextMenu;

public interface ContextMenuRepository extends CrudRepository<ContextMenu, Long> {

	@Query("select m from ContextMenu m where m.code = ?1")
	ContextMenu findByCode(String code);

}
