package cc.wechat.sdk.mp.rest;

import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.springframework.http.MediaType;

import cc.wechat.sdk.api.WeixinApi;
import cc.wechat.sdk.bean.Image;
import cc.wechat.sdk.bean.IpList;
import cc.wechat.sdk.bean.Text;
import cc.wechat.sdk.bean.Token;
import cc.wechat.sdk.bean.msg.in.BaseInMsg;
import cc.wechat.sdk.bean.msg.in.MediaInMsg;
import cc.wechat.sdk.bean.msg.out.BaseOutMsg;
import cc.wechat.sdk.bean.msg.out.ImageOutMsg;
import cc.wechat.sdk.bean.msg.out.MediaOutMsg;
import cc.wechat.sdk.bean.msg.out.TextOutMsg;
import cc.wechat.sdk.exception.FailedToGetTokenException;

public class WeixinApiTest {

	@Test
	public void test_ForToken_getAccessToken() throws FailedToGetTokenException {
		Token token = WeixinApi.ForToken.getAccessToken();
		System.out.println(ToStringBuilder.reflectionToString(token, ToStringStyle.MULTI_LINE_STYLE));
	}
	
	@Test
	public void test_ForToken_getIpList() throws FailedToGetTokenException {
		IpList result = WeixinApi.ForToken.getIpList();
		System.out.println(ToStringBuilder.reflectionToString(result, ToStringStyle.MULTI_LINE_STYLE));
	}
	
	@Test
	public void test_ForSendMsg_customSend_text() throws FailedToGetTokenException {
		TextOutMsg sendMsg = new TextOutMsg();
		sendMsg.setTouser("oODT9jpg8GZG90ewWP3QPDjlKhVE");
		sendMsg.setMsgtype("text");
		sendMsg.setText(new Text("Junit Test 成功！"));
		BaseInMsg result = WeixinApi.ForSendMsg.customSend(sendMsg);
		System.out.println(ToStringBuilder.reflectionToString(result, ToStringStyle.MULTI_LINE_STYLE));
	}
	
	@Test
	public void test_ForSendMsg_customSend_image() throws FailedToGetTokenException {
		ImageOutMsg sendMsg = new ImageOutMsg();
		sendMsg.setTouser("oODT9jpg8GZG90ewWP3QPDjlKhVE");
		sendMsg.setMsgtype("image");
		sendMsg.setImage(new Image("zWZwlw5V6mc20VCu-VDke4JUXdQsxe5AEAfGI50FGMt5pZvCHayIRVlU_H8W5cXl"));
		BaseInMsg result = WeixinApi.ForSendMsg.customSend(sendMsg);
		System.out.println(ToStringBuilder.reflectionToString(result, ToStringStyle.MULTI_LINE_STYLE));
	}
	
	@Test
	public void test_ForMedia_upload() throws FailedToGetTokenException {
		
		MediaOutMsg mediaMsg = new MediaOutMsg();
		mediaMsg.setMediaType(MediaType.IMAGE_JPEG.toString());
		mediaMsg.setPath("E:\\workspace\\j2ee\\wechat-demo\\src\\main\\resources\\mm.jpeg");
		
		BaseInMsg result = WeixinApi.ForMedia.upload(mediaMsg);
		System.out.println(ToStringBuilder.reflectionToString(result, ToStringStyle.MULTI_LINE_STYLE));
	}
	
	@Test
	public void test_ForMedia_getStream() throws FailedToGetTokenException {
		MediaOutMsg msg = new MediaOutMsg();
		msg.setMedia_id("zWZwlw5V6mc20VCu-VDke4JUXdQsxe5AEAfGI50FGMt5pZvCHayIRVlU_H8W5cXl");
		OutputStream out = WeixinApi.ForMedia.getStream(msg);
	}
	
	@Test
	public void test_ForMedia_get() throws FailedToGetTokenException {
		MediaOutMsg msg = new MediaOutMsg();
		msg.setMedia_id("zWZwlw5V6mc20VCu-VDke4JUXdQsxe5AEAfGI50FGMt5pZvCHayIRVlU_H8W5cXl");
		MediaInMsg inMsg = WeixinApi.ForMedia.get(msg);
	}
	
	

}
