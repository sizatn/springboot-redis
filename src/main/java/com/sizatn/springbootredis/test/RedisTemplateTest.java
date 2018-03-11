package com.sizatn.springbootredis.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;

public class RedisTemplateTest {

	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate redisTemplate;

	@SuppressWarnings("unchecked")
	public void findAll() {
		// -----------------String类型数据操作 start--------------------
		ValueOperations<String, String> stringOperations = redisTemplate.opsForValue();
		// String类型数据存储，不设置过期时间，永久性保存
		stringOperations.set("string1", "fiala");
		// String类型数据存储，设置过期时间为80秒，采用TimeUnit控制时间单位
		stringOperations.set("string2", "fiala", 80, TimeUnit.SECONDS);
		// 判断key值是否存在，存在则不存储，不存在则存储
		stringOperations.setIfAbsent("string1", "my fiala");
		stringOperations.setIfAbsent("string3", "my fiala");
		String value1 = stringOperations.get("string1");
		String value2 = stringOperations.get("string3");
		System.out.println(value1);
		System.out.println(value2);
		// -----------------String类型数据操作 end--------------------

		// -----------------其他值类型数据操作 start--------------------
		User User = new User();
		User.setUserNo("1");
		User.setUserName("fiala");
		List<User> demos = new ArrayList<User>();
		ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
		// 设置value为对象类型，且不设置过期时间，默认永久
		valueOperations.set("value1", User);
		// 设置value为对象类型，设置过期时间为80秒，时间单位由TimeUnit控制
		valueOperations.set("value2", demos, 80, TimeUnit.SECONDS);
		User demo1 = (User) valueOperations.get("value1");
		System.out.println(demo1.toString());
		// -----------------其他值类型数据操作 end--------------------

		// -----------------List数据类型操作 start------------------
		ListOperations<String, Object> listOperations = redisTemplate.opsForList();
		for (int i = 0; i < 5; i++) {
			User listDemo = new User();
			listDemo.setUserNo("\"" + i + "\"");
			listDemo.setUserName("fiala" + i);
			listOperations.leftPush("list1", listDemo);
			listOperations.rightPush("list2", listDemo);
		}
		// 可给数据排序
		User demo2 = (User) listOperations.leftPop("list1");
		User demo3 = (User) listOperations.rightPop("list2");
		System.out.println(demo2.toString());
		System.out.println(demo3.toString());
		// -----------------List数据类型操作 end------------------

		// -----------------set数据类型操作 start------------------
		SetOperations<String, Object> setOperations = redisTemplate.opsForSet();
		for (int i = 0; i < 5; i++) {
			User setDemo = new User();
			setDemo.setUserNo("\"" + i + "\"");
			setDemo.setUserName("fiala" + i);
			setOperations.add("set1", setDemo);
		}
		User demo4 = (User) setOperations.pop("set1");
		System.out.println(demo4.toString());
		// -----------------set数据类型操作 end------------------

		// -----------------zset数据类型操作 start------------------
		ZSetOperations<String, Object> zSetOperations = redisTemplate.opsForZSet();
		zSetOperations.add("zset", "fiala", 0);
		zSetOperations.add("zset", "my fiala", 1);
		System.out.println(zSetOperations.rangeByScore("zset", 0, 1));
		// -----------------zset数据类型操作 end------------------

		// -----------------hash数据类型操作 start------------------
		HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
		Map<String, String> map = new HashMap<String, String>();
		map.put("map1", "fiala1");
		map.put("map2", "fiala2");
		hashOperations.putAll("hash", map);
		System.out.println(hashOperations.entries("hash"));
		// -----------------hash数据类型操作 start------------------
	}
}
