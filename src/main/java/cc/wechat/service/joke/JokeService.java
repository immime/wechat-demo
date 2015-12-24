package cc.wechat.service.joke;

import java.util.List;

import cc.wechat.data.domain.Joke;
import cc.wechat.openapi.exception.ApiStoreException;
import cc.wechat.sdk.message.NewsMsg;
import cc.wechat.sdk.message.req.BaseReq;

public interface JokeService {
	/**
	 * 批量持久化
	 */
	void batchPersist();
	Joke queryOneJoke();
	List<Joke> queryBatchJokes();
	NewsMsg queryJokeNewsMsg(BaseReq req) throws ApiStoreException;
}
