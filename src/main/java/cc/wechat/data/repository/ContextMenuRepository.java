package cc.wechat.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import cc.wechat.data.domain.ContextMenu;

public interface ContextMenuRepository extends CrudRepository<ContextMenu, String> {

//	@Query("select m from ContextMenu m where m.code = ?1")
//	ContextMenu findByCode(String code);
	
	@Query("SELECT CASE WHEN COUNT(m) > 0 THEN 'true' ELSE 'false' END FROM ContextMenu m WHERE m.code = ?1")
	Boolean exisitsCode(String code);

}
