package pers.zcy.myblogauthorize.config;

import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pers.zcy.myblogauthorize.realm.UserRealm;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class MyShiroConfig {

    private static final String salt="17626rttrg78583trtrr";

    @Autowired
    private SessionManager sessionManager;

    @Autowired
    private RedisCacheManager redisCacheManager;

    @Bean
    public ByteSource myCredentialsSalt(){

        return ByteSource.Util.bytes(salt);
    }

    @Bean
    public CredentialsMatcher myCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }

    @Bean
    public UserRealm userRealm(){
        UserRealm userRealm = new UserRealm();
        return userRealm;
    }

    @Bean
    public DefaultWebSecurityManager mySecurityManager(UserRealm userRealm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(userRealm);
        defaultWebSecurityManager.setSessionManager(sessionManager);
        defaultWebSecurityManager.setCacheManager(redisCacheManager);
        return defaultWebSecurityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager mySecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(mySecurityManager);
        shiroFilterFactoryBean.setLoginUrl("/admin/login");
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/admin/login","anon");
        filterMap.put("/admin/*","authc");
        filterMap.put("/*","anon");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }
}
