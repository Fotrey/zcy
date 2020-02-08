package pers.zcy.myblogboot.config.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.data.redis.serializer.StringRedisSerializer;


@Configuration
@EnableCaching
public class MyRedisConfig {

    @Autowired
    private Jackson2JsonRedisSerializer jackson2JsonRedisSerializer;

    @Autowired
    private StringRedisSerializer stringRedisSerializer;

    @Bean
    @Primary
    public RedisTemplate<Object, Object> myRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(stringRedisSerializer);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashKeySerializer(stringRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.setEnableTransactionSupport(true);
        template.afterPropertiesSet();
        return template;
    }



    @Bean
    public KeyGenerator myKeyGenerator(){
        return (target, method, params) -> "["+target.toString()+": "+method.getName()+"]";
    }

}
