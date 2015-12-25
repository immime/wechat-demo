package cc.wechat.service.joke;

import java.util.List;

import cc.wechat.data.domain.Joke;
import cc.wechat.openapi.exception.ApiStoreException;
import cc.wechat.sdk.message.NewsMsg;
import cc.wechat.sdk.message.TextMsg;
import cc.wechat.sdk.message.req.BaseReq;

public interface JokeService {
	/**
	 * 批量持久化
	 * @param jokes TODO
	 */
	void batchPersist(List<Joke> jokes);
	/**
	 * 随机获取一条记录
	 * @return
	 */
	Joke findRandomOne();
	/**
	 * 返回一条笑话的TextMsg
	 * @return
	 */
	TextMsg queryOneJokeTextMsg();
	NewsMsg queryJokeNewsMsg(BaseReq req);
}
