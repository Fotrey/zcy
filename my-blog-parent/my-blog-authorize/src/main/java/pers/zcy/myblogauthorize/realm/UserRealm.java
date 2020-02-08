package pers.zcy.myblogauthorize.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import pers.zcy.myblogauthorize.service.UserService;
import pers.zcy.myblogboot.entity.User;

import javax.annotation.Resource;


public class UserRealm extends AuthenticatingRealm {

    @Resource
    ByteSource myCredentialsSalt;

    @Autowired
    UserService userService;

    @Autowired
    @Qualifier("myCredentialsMatcher")
    CredentialsMatcher myCredentialsMatcher;

    @Override
    public AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        setCredentialsMatcher(myCredentialsMatcher);
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)authenticationToken;
        User user = userService.checkUser((String)usernamePasswordToken.getPrincipal());
        return null != user?new SimpleAuthenticationInfo(user,user.getPassword(),myCredentialsSalt,getName()):null;
    }

}
