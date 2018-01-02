package com.sizatn.springbootredis.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {

	@Autowired
	private RedisProperties redisProperties;

	@Bean
	@Primary
	public JedisPoolConfig jedisZeroPoolConfig() {
		JedisPoolConfig jpc = new JedisPoolConfig();
		jpc.setMaxIdle(redisProperties.getPool().getMaxIdle());
		jpc.setMinIdle(redisProperties.getPool().getMinIdle());
		jpc.setMaxWaitMillis(redisProperties.getPool().getMaxWait());
		jpc.setMaxTotal(redisProperties.getPool().getMaxActive());
		return jpc;
	}

	@Bean
	@Primary
	public JedisConnectionFactory jedisZeroConnectionFactory() {
		JedisConnectionFactory jcf = new JedisConnectionFactory();
		jcf.setDatabase(redisProperties.getDatabase());
		jcf.setHostName(redisProperties.getHost());
		jcf.setPort(redisProperties.getPort());
		jcf.setPoolConfig(jedisZeroPoolConfig());
		return jcf;
	}

	@Bean
	@Primary
	public StringRedisTemplate redisZeroTemplate() {
		StringRedisTemplate srt = new StringRedisTemplate();
		srt.setConnectionFactory(jedisZeroConnectionFactory());
		return srt;
	}

}
