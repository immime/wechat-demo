package cc.wechat.service.joke;

import java.util.List;

import cc.wechat.openapi.exception.ApiStoreException;
import cc.wechat.sdk.message.NewsMsg;
import cc.wechat.sdk.message.req.BaseReq;
import cc.wechat.service.joke.bean.Joke;

public interface IJokeService {
	Joke queryOneJoke();
	List<Joke> queryBatchJokes();
	NewsMsg queryJokeNewsMsg(BaseReq req) throws ApiStoreException;
}
