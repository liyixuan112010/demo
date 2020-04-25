package com.xuan.springbootdemo;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringbootdemoApplicationTests {

    @Resource
    private RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("key","value");
        System.out.println("成功");
    }

}
