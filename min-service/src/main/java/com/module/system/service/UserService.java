package com.module.system.service;

import com.module.system.domain.User;

public interface UserService {
    User findUserByName(String username);
}
