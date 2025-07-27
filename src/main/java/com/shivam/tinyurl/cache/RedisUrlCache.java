package com.shivam.tinyurl.cache;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisUrlCache {

    private final RedisTemplate<String,String> redisTemplate;

    public RedisUrlCache(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void cacheShortToLong(String shortCode, String longUrl) {
        redisTemplate.opsForValue().set(shortCode, longUrl);
    }

    public String getLongUrl(String shortCode) {
        return redisTemplate.opsForValue().get(shortCode);
    }
}
