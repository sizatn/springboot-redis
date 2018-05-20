package com.sizatn.springbootredis.configuration.after_2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.sizatn.springbootredis.properties.after_2.RedisOneProperties;

@Configuration
@EnableConfigurationProperties(RedisOneProperties.class)
public class RedisOneConfig {

	@Autowired
	private RedisOneProperties redisOneProperties;

	@Bean
	public LettuceConnectionFactory lettuceOneConnectionFactory() {
		LettuceConnectionFactory lcf = new LettuceConnectionFactory(redisOneStandaloneConfiguration());
		return lcf;
	}

	@Bean
	public RedisStandaloneConfiguration redisOneStandaloneConfiguration() {
		RedisStandaloneConfiguration rsc = new RedisStandaloneConfiguration();
		rsc.setDatabase(redisOneProperties.getDatabase());
		rsc.setHostName(redisOneProperties.getHost());
		rsc.setPassword(RedisPassword.of(redisOneProperties.getPassword()));
		return rsc;
	}

	@Bean
	public StringRedisTemplate redisOneTemplate() {
		return new StringRedisTemplate(lettuceOneConnectionFactory());
	}
}
