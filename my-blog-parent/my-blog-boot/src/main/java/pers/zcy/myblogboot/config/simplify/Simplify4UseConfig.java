package pers.zcy.myblogboot.config.simplify;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class Simplify4UseConfig {

    @Bean
    public Jackson2JsonRedisSerializer jackson2JsonRedisSerializer(){
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        return jackson2JsonRedisSerializer;
    }

    @Bean
    public StringRedisSerializer stringRedisSerializer(){
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        return stringRedisSerializer;
    }

}
