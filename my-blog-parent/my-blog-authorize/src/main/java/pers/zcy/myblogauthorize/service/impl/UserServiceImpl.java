package pers.zcy.myblogauthorize.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pers.zcy.myblogauthorize.dao.UserRepository;
import pers.zcy.myblogauthorize.service.UserService;
import pers.zcy.myblogboot.entity.User;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username) {

        return userRepository.findByUsername(username);
    }
}
