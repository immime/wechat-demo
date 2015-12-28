package cc.wechat.openapi;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

import cc.wechat.constant.WechatConsts;

public class ApiStoreClientTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testGet() {
		String inputMsg = "你好";
		String path = "/turing/turing/turing";
		
		Map<String, String> urlParams = new HashMap<String, String>();
		urlParams.put("info", inputMsg);
		urlParams.put("key", "879a6cb3afb84dbf4fc84a1df2ab7319");
		JSONObject bodyJsonObj = ApiStoreClient.get(path, urlParams);
		
		assertNotNull(bodyJsonObj);
		
	}

}
