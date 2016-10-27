package cc.wechat.module.joke.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import cc.wechat.data.domain.Joke;

public interface JokeRepository extends CrudRepository<Joke, Long> {
	
	Page<Joke> findAll(Pageable page);

}
