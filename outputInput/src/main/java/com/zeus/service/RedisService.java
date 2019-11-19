package com.zeus.service;

import cn.hutool.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * redis操作类
 * @author tumu
 */
@Service
public class RedisService {
	
	@Autowired
	private StringRedisTemplate template;
	
	/**
	 * redis存入String，String类型，obj转json串存入
	 * @param key
	 * @param obj
	 */
	public void set(String key, Object obj) {
		String objJson = JSONUtil.toJsonStr(obj);
		template.opsForValue().set(key,objJson);
	}
	
	/**
	 * 根据key取出redis的value，obj对象的json串格式转成obj
	 * @param key
	 * @param clazz
	 * @return
	 */
	public <T>T get(String key, Class<T> clazz){
		String json = template.opsForValue().get(key);
		T obj =  JSONUtil.toBean(json,clazz);
		return obj;
	}

	/**
	 *  String类型值直接取出，不需要转换
	 * @param key 键
	 * @return json
	 */
	public String getString(String key){
		String json = template.opsForValue().get(key);
		return json;
	}
	
	/**
	 * 设置有效时长
	 * @param key
	 * @param seconds 秒
	 */
	public void expire(String key, int seconds) {
		template.expire(key, seconds, TimeUnit.SECONDS);
	}

	/**
	 * 删除key
	 * @param key 键
	 */
	public void delete(String key){
		template.delete(key);
	}
	
	
}
