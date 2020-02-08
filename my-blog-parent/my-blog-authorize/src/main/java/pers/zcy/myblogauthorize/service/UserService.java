package pers.zcy.myblogauthorize.service;

import pers.zcy.myblogboot.entity.User;

public interface UserService {
    User checkUser(String username);
}
