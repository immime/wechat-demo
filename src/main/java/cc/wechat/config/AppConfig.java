package cc.wechat.config;

import java.util.List;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(
        value = "cc.wechat",
        includeFilters = @ComponentScan.Filter({Controller.class, RestController.class, Aspect.class}),
        useDefaultFilters = false)
public class AppConfig extends WebMvcConfigurerAdapter {
	/**
	 * Add JSON MessageConverter to send JSON objects to web clients.
	 */
	@Override
	public void configureMessageConverters(
			List<HttpMessageConverter<?>> converters) {
		converters.add(new MappingJackson2HttpMessageConverter());
	}
	
}
