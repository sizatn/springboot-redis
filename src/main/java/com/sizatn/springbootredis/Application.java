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

		// 通过beanname获取对象
		// 为什么会只写入database:0呢？
		StringRedisTemplate srt = (StringRedisTemplate) context.getBean("redisTemplate");
		StringRedisTemplate srt1 = (StringRedisTemplate) context.getBean("redisOneTemplate");
		
		ValueOperations<String, String> v = srt.opsForValue();
		v.set("aaa", "111");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("ddd", "333");
		map.put("eee", "444");
		map.put("fff", "555");
		srt1.executePipelined((RedisCallback<Object>) conn -> {
			map.forEach((key, value) -> {
				conn.set(key.getBytes(), value.getBytes());
			});
			return null;
		});
	}

}
