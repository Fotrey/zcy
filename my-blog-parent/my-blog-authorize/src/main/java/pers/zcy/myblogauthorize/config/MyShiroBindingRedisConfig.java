package pers.zcy.myblogauthorize.config;



import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;


import org.crazycake.shiro.RedisSessionDAO;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MyShiroBindingRedisConfig {

    @Value("${my.redis.host}")
    private String host = "120.24.73.91:9736";
    @Value("${my.redis.password}")
    private String password = "ZCYbcptdtptp7600";

    @Bean
    public RedisManager redisManager(){
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host);
        redisManager.setPassword(password);
        return redisManager;
    }

    @Bean
    public RedisSessionDAO redisSessionDAO(RedisManager redisManager){
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager);
        return redisSessionDAO;
    }

    @Bean
    public RedisCacheManager redisCacheManager(RedisManager redisManager){
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager);
        return redisCacheManager;
    }

    @Bean
    public SessionManager sessionManager(RedisSessionDAO redisSessionDAO){
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO);
        return sessionManager;
    }
}
