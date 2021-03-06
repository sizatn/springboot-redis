package com.sizatn.springbootredis.configuration.before_2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.sizatn.springbootredis.properties.before_2.RedisOneProperties;

import redis.clients.jedis.JedisPoolConfig;

//@Configuration
//@EnableConfigurationProperties(RedisOneProperties.class)
public class RedisOneConfig {

//	@Autowired
//	private RedisOneProperties redisProperties;
//
//	@Bean
//	public JedisPoolConfig jedisOnePoolConfig() {
//		JedisPoolConfig jpc = new JedisPoolConfig();
//		jpc.setMaxIdle(redisProperties.getPool().getMaxIdle());
//		jpc.setMinIdle(redisProperties.getPool().getMinIdle());
//		jpc.setMaxWaitMillis(redisProperties.getPool().getMaxWait());
//		jpc.setMaxTotal(redisProperties.getPool().getMaxActive());
//		return jpc;
//	}
//
//	@Bean
//	public JedisConnectionFactory jedisOneConnectionFactory() {
//		JedisConnectionFactory jcf = new JedisConnectionFactory();
//		return jcf;
//	}
//
//	@Bean
//	public StringRedisTemplate redisOneTemplate() {
//		StringRedisTemplate srt = new StringRedisTemplate();
//		srt.setConnectionFactory(jedisOneConnectionFactory());
//		return srt;
//	}

}
