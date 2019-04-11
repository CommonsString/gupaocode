package com.module.system.service;

import com.module.system.entity.User;

public interface UserService {
    User findUserByName(String username);
}
