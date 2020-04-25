package com.xuan.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Arrays;

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return (target,methdod,params) -> {
            StringBuilder str = new StringBuilder();
            str.append(target.getClass().getName());
            str.append(":");
            str.append(methdod.getName());
            for (Object obj : params) {
                str.append("-");
                str.append(obj.toString());
            }
            return str.toString();
        };
    }


    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory){
        RedisCacheManager cacheManager = RedisCacheManager.create(factory);
        return cacheManager;
    }


    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory factory){
        RedisTemplate<Object, Object> template=new RedisTemplate<Object, Object>();
        template.setConnectionFactory(factory);
        template.setKeySerializer(new StringRedisSerializer());
        return template;
    }
}
