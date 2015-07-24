package cc.wechat.sdk.mp.rest;

import java.io.OutputStream;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.springframework.http.MediaType;

import cc.wechat.sdk.api.TokenStore;
import cc.wechat.sdk.api.WechatApi;
import cc.wechat.sdk.bean.Image;
import cc.wechat.sdk.bean.IpList;
import cc.wechat.sdk.bean.Text;
import cc.wechat.sdk.bean.msg.in.BaseInMsg;
import cc.wechat.sdk.bean.msg.in.MaterialInMsg;
import cc.wechat.sdk.bean.msg.in.MediaInMsg;
import cc.wechat.sdk.bean.msg.in.Token;
import cc.wechat.sdk.bean.msg.out.ImageOutMsg;
import cc.wechat.sdk.bean.msg.out.MaterialOutMsg;
import cc.wechat.sdk.bean.msg.out.MaterialOutMsg.Description;
import cc.wechat.sdk.bean.msg.out.MediaOutMsg;
import cc.wechat.sdk.bean.msg.out.TextOutMsg;
import cc.wechat.sdk.exception.ApiException;
import cc.wechat.sdk.exception.FailedToGetTokenException;

public class WeixinApiTest {

	@Test
	public void test_ForToken_getAccessToken() throws FailedToGetTokenException {
		Token token = WechatApi.ForToken.getAccessToken();
		System.out.println(ToStringBuilder.reflectionToString(token, ToStringStyle.MULTI_LINE_STYLE));
	}
	
	@Test
	public void test_ForToken_getIpList() throws FailedToGetTokenException {
		IpList result = WechatApi.ForToken.getIpList();
		System.out.println(ToStringBuilder.reflectionToString(result, ToStringStyle.MULTI_LINE_STYLE));
	}
	
	@Test
	public void test_ForSendMsg_customSend_text() throws FailedToGetTokenException {
		TextOutMsg sendMsg = new TextOutMsg();
		sendMsg.setTouser("oODT9jpg8GZG90ewWP3QPDjlKhVE");
		sendMsg.setMsgtype("text");
		sendMsg.setText(new Text("Junit Test 成功！"));
		BaseInMsg result = WechatApi.ForSendMsg.customSend(sendMsg);
		System.out.println(ToStringBuilder.reflectionToString(result, ToStringStyle.MULTI_LINE_STYLE));
	}
	
	@Test
	public void test_ForSendMsg_customSend_image() throws FailedToGetTokenException {
		ImageOutMsg sendMsg = new ImageOutMsg();
		sendMsg.setTouser("oODT9jpg8GZG90ewWP3QPDjlKhVE");
		sendMsg.setMsgtype("image");
		sendMsg.setImage(new Image("zWZwlw5V6mc20VCu-VDke4JUXdQsxe5AEAfGI50FGMt5pZvCHayIRVlU_H8W5cXl"));
		BaseInMsg result = WechatApi.ForSendMsg.customSend(sendMsg);
		System.out.println(ToStringBuilder.reflectionToString(result, ToStringStyle.MULTI_LINE_STYLE));
	}
	
	@Test
	public void test_ForMedia_upload() throws FailedToGetTokenException {
		
		MediaOutMsg mediaMsg = new MediaOutMsg();
		mediaMsg.setType(MediaType.IMAGE_JPEG.toString());
		mediaMsg.setLocalPath("E:\\workspace\\j2ee\\wechat-demo\\src\\main\\resources\\mm.jpeg");
		
		BaseInMsg result = WechatApi.ForMedia.upload(mediaMsg);
		System.out.println(ToStringBuilder.reflectionToString(result, ToStringStyle.MULTI_LINE_STYLE));
	}
	
	@Test
	public void test_ForMedia_get() throws FailedToGetTokenException {
		MediaOutMsg msg = new MediaOutMsg();
		msg.setMedia_id("zWZwlw5V6mc20VCu-VDke4JUXdQsxe5AEAfGI50FGMt5pZvCHayIRVlU_H8W5cXl");
		MediaInMsg inMsg = WechatApi.ForMedia.get(msg);
		System.out.println(ToStringBuilder.reflectionToString(inMsg, ToStringStyle.MULTI_LINE_STYLE));
	}
	
	@Test
	public void test_ForMaterial_addMaterial() throws FailedToGetTokenException {
//		image
//		MaterialOutMsg out = new MaterialOutMsg();
//		out.setAccess_token(TokenStore.get());
//		out.setType("image");
//		out.setLocalPath("C:\\Users\\Administrator\\Desktop\\icon.png");
//		MaterialInMsg inMsg = WechatApi.ForMaterial.add(out);
		
//		voice
		MaterialOutMsg out = new MaterialOutMsg();
		out.setAccess_token(TokenStore.get());
		out.setType("voice");
		out.setLocalPath("E:\\workspace\\j2ee\\wechat-demo\\src\\main\\resources\\mm.mp3");
		MaterialInMsg inMsg = WechatApi.ForMaterial.add(out);	
		System.out.println(ToStringBuilder.reflectionToString(inMsg, ToStringStyle.MULTI_LINE_STYLE));
	}
	
	@Test
	public void test_ForMaterial_addMaterialVideo() throws FailedToGetTokenException {
		MaterialOutMsg out = new MaterialOutMsg();
		out.setAccess_token(TokenStore.get());
		out.setType("video");
		out.setLocalPath("C:\\Users\\Administrator\\Desktop\\video.mp4");
		Description des = new MaterialOutMsg().new Description();
		des.setTitle("视频标题");
		des.setIntroduction("视频描述");
		out.setDescription(des);
		MaterialInMsg inMsg = WechatApi.ForMaterial.add(out);
		System.out.println(ToStringBuilder.reflectionToString(inMsg, ToStringStyle.MULTI_LINE_STYLE));
	}
	
	@Test 
	public void test_ForMaterial_get() throws ApiException {
		MaterialOutMsg msg = new MaterialOutMsg();
		msg.setMedia_id("-MUMYJ1QSJ1EBZTtJA8HWz8r5d87ToqO47f7tFSvihU");
		MaterialInMsg inMsg = WechatApi.ForMaterial.get(msg);
		System.out.println(ToStringBuilder.reflectionToString(inMsg, ToStringStyle.MULTI_LINE_STYLE));
	}
	
	
}
