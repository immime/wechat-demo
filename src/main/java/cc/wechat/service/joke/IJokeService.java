package cc.wechat.service.joke;

import cc.wechat.service.IWechatService;
import cc.wechat.service.joke.bean.Joke;

public interface IJokeService extends IWechatService {
	Joke getRandomJoke();
}
