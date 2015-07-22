package cc.wechat.sdk.mp.rest;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import cc.wechat.sdk.mp.rest.entity.Token;
import cc.wechat.sdk.mp.rest.entity.msg.send.BaseSendMsg;
import cc.wechat.sdk.mp.rest.entity.msg.send.Image;
import cc.wechat.sdk.mp.rest.entity.msg.send.ImageSendMsg;
import cc.wechat.sdk.mp.rest.entity.msg.send.MediaSendMsg;
import cc.wechat.sdk.mp.rest.entity.msg.send.Text;
import cc.wechat.sdk.mp.rest.entity.msg.send.TextSendMsg;
import cc.wechat.sdk.mp.rest.exception.ApiGetTokenFailedException;

public class WeixinApiTest {

	@Test
	public void test_ForToken_getAccessToken() throws ApiGetTokenFailedException {
		Token token = WeixinApi.ForToken.getAccessToken();
		System.out.println(ToStringBuilder.reflectionToString(token, ToStringStyle.MULTI_LINE_STYLE));
	}
	
	@Test
	public void test_ForToken_getIpList() throws ApiGetTokenFailedException {
		String result = WeixinApi.ForToken.getIpList();
		System.out.println(result);
	}
	
	@Test
	public void test_ForSendMsg_customSend_text() throws ApiGetTokenFailedException {
		TextSendMsg sendMsg = new TextSendMsg();
		sendMsg.setTouser("oODT9jpg8GZG90ewWP3QPDjlKhVE");
		sendMsg.setMsgtype("text");
		sendMsg.setText(new Text("dabadbab"));
		BaseSendMsg result = WeixinApi.ForSendMsg.customSend(sendMsg);
		System.out.println(ToStringBuilder.reflectionToString(result, ToStringStyle.MULTI_LINE_STYLE));
	}
	
	@Test
	public void test_ForSendMsg_customSend_image() throws ApiGetTokenFailedException {
		ImageSendMsg sendMsg = new ImageSendMsg();
		sendMsg.setTouser("oODT9jpg8GZG90ewWP3QPDjlKhVE");
		sendMsg.setMsgtype("image");
		sendMsg.setImage(new Image("JJKM-rpjQtwSucJ9sy1UFvyd5MYTIdtgY1BeVvTTX3O0SUVA03Oh_fa59dGEj_ZI"));
		BaseSendMsg result = WeixinApi.ForSendMsg.customSend(sendMsg);
		System.out.println(ToStringBuilder.reflectionToString(result, ToStringStyle.MULTI_LINE_STYLE));
	}
	
	@Test
	public void test_ForMedia_upload() throws ApiGetTokenFailedException {
		
		MediaSendMsg mediaMsg = new MediaSendMsg();
		mediaMsg.setMediaType(MediaType.IMAGE_JPEG.toString());
		mediaMsg.setPath("E:\\workspace\\j2ee\\wechat-demo\\src\\main\\resources\\mm.jpeg");
		
		BaseSendMsg result = WeixinApi.ForMedia.upload(mediaMsg);
		System.out.println(ToStringBuilder.reflectionToString(result, ToStringStyle.MULTI_LINE_STYLE));
	}
	

}
