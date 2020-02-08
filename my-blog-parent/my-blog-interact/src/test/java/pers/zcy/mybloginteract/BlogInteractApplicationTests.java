package pers.zcy.mybloginteract;

import lombok.Setter;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import org.springframework.test.context.junit4.SpringRunner;

import pers.zcy.myblogauthorize.dao.UserRepository;
import pers.zcy.myblogauthorize.realm.UserRealm;
import pers.zcy.myblogauthorize.service.UserService;
import pers.zcy.myblogboot.entity.Tag;
import pers.zcy.myblogboot.persist.TypeRepository;
import pers.zcy.myblogboot.utils.BeanFieldJudgeUtils;


import javax.sql.DataSource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogInteractApplicationTests {
    @Autowired
    DataSource dataSource;

@Autowired
    UserRealm userRealm;
    @Autowired
            @Setter
    UserRepository userRepository;
    @Autowired
            @Qualifier("myRedisTemplate")
    RedisTemplate myRedisTemplate;

    @Autowired
    UserService userService;
    @Autowired
    @Qualifier("redisTemplate")
    RedisTemplate redisTemplate;
    @Autowired
    DefaultSecurityManager securityManager;

@Autowired
    TypeRepository typeRepository;
    @Test
    public void contextLoads() {
        Tag tag1 = new Tag();
        tag1.setId(111L);
        String[] nullPropertyNames = BeanFieldJudgeUtils.getNullPropertyNames(tag1);
        for (String s:nullPropertyNames
             ) {
            System.out.println(s);
        }
    }

}
