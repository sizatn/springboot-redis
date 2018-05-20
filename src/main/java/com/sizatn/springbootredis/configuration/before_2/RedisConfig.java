package com.sizatn.springbootredis.configuration.before_2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import redis.clients.jedis.JedisPoolConfig;

//@Configuration
public class RedisConfig {

//	@Autowired
//	private RedisProperties redisProperties;
//
//	@Bean
//	@Primary
//	public JedisPoolConfig jedisPoolConfig() {
//		JedisPoolConfig jpc = new JedisPoolConfig();
//		jpc.setMaxIdle(redisProperties.getPool().getMaxIdle());
//		jpc.setMinIdle(redisProperties.getPool().getMinIdle());
//		jpc.setMaxWaitMillis(redisProperties.getPool().getMaxWait());
//		jpc.setMaxTotal(redisProperties.getPool().getMaxActive());
//		return jpc;
//	}
//
//	@Bean
//	@Primary
//	public JedisConnectionFactory jedisConnectionFactory() {
//		JedisConnectionFactory jcf = new JedisConnectionFactory();
//		jcf.setDatabase(redisProperties.getDatabase());
//		jcf.setHostName(redisProperties.getHost());
//		jcf.setPort(redisProperties.getPort());
//		jcf.setPoolConfig(jedisPoolConfig());
//		return jcf;
//	}
//
//	@Bean
//	@Primary
//	public StringRedisTemplate stringRedisTemplate() {
//		StringRedisTemplate srt = new StringRedisTemplate();
//		srt.setConnectionFactory(jedisConnectionFactory());
//		return srt;
//	}

}
