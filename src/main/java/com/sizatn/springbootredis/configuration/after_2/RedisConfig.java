package com.sizatn.springbootredis.configuration.after_2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
public class RedisConfig {

	@Autowired
	private RedisProperties redisProperties;

	@Bean
	@Primary
	public LettuceConnectionFactory lettuceConnectionFactory() {
		LettuceConnectionFactory lcf = new LettuceConnectionFactory(redisStandaloneConfiguration());
		return lcf;
	}

	@Bean
	@Primary
	public RedisStandaloneConfiguration redisStandaloneConfiguration() {
		RedisStandaloneConfiguration rsc = new RedisStandaloneConfiguration();
		rsc.setDatabase(redisProperties.getDatabase());
		rsc.setHostName(redisProperties.getHost());
		rsc.setPassword(RedisPassword.of(redisProperties.getPassword()));
		return rsc;
	}

	@Bean
	@Primary
	public StringRedisTemplate redisTemplate() {
		return new StringRedisTemplate(lettuceConnectionFactory());
	}

}
