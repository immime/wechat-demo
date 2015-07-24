package cc.wechat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableRedisHttpSession
public class RedisSessionConfig {
	@Bean
	public JedisConnectionFactory connectionFactory(@RedisServerPort int port) {
		JedisConnectionFactory connection = new JedisConnectionFactory();
		connection.setPort(port);
		return connection;
	}
}
