package com.sizatn.springbootredis;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		StringRedisTemplate rt0 = (StringRedisTemplate) context.getBean("redisTemplate");
		StringRedisTemplate rt1 = (StringRedisTemplate) context.getBean("redisOneTemplate");
		StringRedisTemplate rt2 = (StringRedisTemplate) context.getBean("redisTwoTemplate");
		
		ValueOperations<String, String> v0 = rt0.opsForValue();
		v0.set("aaa", "111");
		
		ValueOperations<String, String> v1 = rt1.opsForValue();
		v1.set("bbb", "222");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("ddd", "333");
		map.put("eee", "444");
		map.put("fff", "555");
		rt2.executePipelined((RedisCallback<Object>) conn -> {
			map.forEach((key, value) -> {
				conn.set(key.getBytes(), value.getBytes());
			});
			return null;
		});
	}

}
