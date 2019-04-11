package com.module.system.service.Impl;

import com.module.security.utils.ValidUtils;
import com.module.system.dto.mapper.UserMapper;
import com.module.system.entity.User;
import com.module.system.repository.UserRepository;
import com.module.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userDao;

    @Autowired
    private UserMapper userMapper;


    /**
     * 根据姓名查找用户
     * @param username
     * @return
     */
    @Override
    public User findUserByName(String username) {
        if("".equals(username) || username == null) {
            throw new RuntimeException("username valid!");
        }
        User user = null;
        // 邮箱
        if(ValidUtils.isEmail(username)) {
            user = userDao.findByEmail(username);
        } else {
            user = userDao.findByUsername(username);
        }
        // 用户null
        if(user == null) {
            throw new RuntimeException("user null！");
        }
        return user;
    }
}
