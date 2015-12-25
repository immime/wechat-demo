/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

import cc.wechat.data.domain.Joke;
import cc.wechat.data.service.sample.CityService;
import cc.wechat.openapi.ApiStoreClient;
import cc.wechat.service.joke.JokeService;

@Controller
public class SampleController {

	@Autowired
	private CityService cityService;
	@Autowired
	private JokeService jokeService;

	@RequestMapping("/jpa")
	@ResponseBody
	@Transactional(readOnly = true)
	public String helloWorld() {
		return this.cityService.getCity("Bath", "UK").getName();
	}
	
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

}