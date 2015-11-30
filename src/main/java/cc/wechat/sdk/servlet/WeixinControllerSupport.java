package cc.wechat.sdk.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cc.wechat.sdk.exception.WeixinException;
import cc.wechat.web.WechatController;

/**
 * 微信公众平台交互操作基类，提供几乎所有微信公众平台交互方式
 * 基于springmvc框架，方便使用此框架的项目集成
 *
 * @author peiyu
 */
@Controller
public abstract class WeixinControllerSupport extends WeixinSupport {
	private static final Logger logger = LoggerFactory.getLogger(WeixinControllerSupport.class);
	
    /**
     * 绑定微信服务器</br>
     * <pre>
     *   务必设置produces="text/html;charset=UTF-8"，
     *   否则验证返回结果以默认json视图返回，导致验证合法但微信服务器不能正确解析。
     * </pre>
     * 
     * @param request 请求
     * @return 响应内容
     */
    @RequestMapping(method = RequestMethod.GET, produces="text/html;charset=UTF-8")
    @ResponseBody
    protected final String bind(HttpServletRequest request) {
        if (isLegal(request)) {
            //绑定微信服务器成功
        	logger.debug("SUCCESS：绑定微信服务器成功-" + new Date());
        	logger.debug("echostr：" + request.getParameter("echostr"));
            return request.getParameter("echostr");
        } else {
            //绑定微信服务器失败
        	logger.debug("ERROR:绑定微信服务器失败-" + new Date());
            return "";
        }
    }

    /**
     * 微信消息交互处理
     *
     * @param request http 请求对象
     * @param response http 响应对象
     * @throws ServletException 异常
     * @throws IOException      IO异常
     */
    @RequestMapping(method = RequestMethod.POST)
    protected final void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (isLegal(request)) {
            String result = processRequest(request);
            //设置正确的 content-type 以防止中文乱码
            response.setContentType("text/xml;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.write(result);
            writer.close();
        } else {
        	throw new WeixinException("未经验证的请求");
        }
    }
}