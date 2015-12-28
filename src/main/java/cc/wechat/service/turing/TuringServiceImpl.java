package cc.wechat.service.turing;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSONObject;

import cc.wechat.openapi.ApiStoreClient;
import cc.wechat.sdk.message.TextMsg;

@Service
public class TuringServiceImpl implements TuringService {
	
	private static final Logger logger = LoggerFactory.getLogger(TuringServiceImpl.class);
	
	@Override
	public TextMsg queryRobotMsg(String inputMsg) {
		Assert.hasText(inputMsg, "'inputMsg' must not be empty");
		String path = "/turing/turing/turing?key={key}&info={info}";
		
		Map<String, String> urlParams = new HashMap<String, String>();
		urlParams.put("key", "879a6cb3afb84dbf4fc84a1df2ab7319");
		urlParams.put("info", inputMsg);
		JSONObject bodyJsonObj = ApiStoreClient.get(path, urlParams);
		
		String resCode = bodyJsonObj.getString("code"); 
		// 返回text
		String text = "";
		if(StringUtils.isNotEmpty(resCode) && resCode.equals("100000")) {
			text = bodyJsonObj.getString("text");
		}
		TextMsg msg = new TextMsg(text);
		return msg;
	}
	
}
