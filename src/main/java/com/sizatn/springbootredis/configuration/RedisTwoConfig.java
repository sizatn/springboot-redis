package com.sizatn.springbootredis.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.sizatn.springbootredis.properties.RedisTwoProperties;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
@EnableConfigurationProperties(RedisTwoProperties.class)
public class RedisTwoConfig {

	@Autowired
	private RedisTwoProperties redisProperties;

	@Bean
	public JedisPoolConfig jedisTwoPoolConfig() {
		JedisPoolConfig jpc = new JedisPoolConfig();
		jpc.setMaxIdle(redisProperties.getPool().getMaxIdle());
		jpc.setMinIdle(redisProperties.getPool().getMinIdle());
		jpc.setMaxWaitMillis(redisProperties.getPool().getMaxWait());
		jpc.setMaxTotal(redisProperties.getPool().getMaxActive());
		return jpc;
	}

	@Bean
	public JedisConnectionFactory jedisTwoConnectionFactory() {
		JedisConnectionFactory jcf = new JedisConnectionFactory();
		jcf.setDatabase(redisProperties.getDatabase());
		jcf.setHostName(redisProperties.getHost());
		jcf.setPort(redisProperties.getPort());
		jcf.setPoolConfig(jedisTwoPoolConfig());
		return jcf;
	}

	@Bean
	public StringRedisTemplate redisTwoTemplate() {
		StringRedisTemplate srt = new StringRedisTemplate();
		srt.setConnectionFactory(jedisTwoConnectionFactory());
		return srt;
	}

}
