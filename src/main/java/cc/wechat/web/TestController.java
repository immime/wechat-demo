package cc.wechat.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cc.wechat.data.domain.ContextMenu;
import cc.wechat.data.domain.Joke;
import cc.wechat.openapi.ApiStoreClient;
import cc.wechat.service.joke.JokeService;
import cc.wechat.service.menu.ContextMenuService;

@Controller
public class TestController {
	@Autowired
	private JokeService jokeService;
	@Autowired
	private ContextMenuService contextMenuService;
	
	@RequestMapping("/joke/init")
	@ResponseBody
	@Transactional(readOnly = true)
	public String saveJokes() {
		JSONObject bodyJsonObj =  ApiStoreClient.post("/showapi_joke/joke_text?page=1&maxResult=100", null);
		JSONArray result = bodyJsonObj.getJSONArray("contentlist"); 
		List<Joke> jokes= JSON.parseArray(result.toJSONString(),Joke.class);
		this.jokeService.batchPersist(jokes);
		return "success";
	}
	
	@RequestMapping("/joke/random/{lastId}")
	@ResponseBody
	@Transactional(readOnly = true)
	public String randomJoke(@PathVariable Long lastId) {
		Joke joke = this.jokeService.findRandomOne();
		return joke.toString();
	}
	
	@RequestMapping("/menu/find/{code}")
	@ResponseBody
	@Transactional(readOnly = true)
	public String findMenuByCode(@PathVariable String code) {
		ContextMenu menu = contextMenuService.findByCode(code);
		return menu.toString();
	}
}
