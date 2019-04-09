package com.controller;


import com.module.system.entity.User;
import com.module.system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class TestController {


    @Autowired
    private UserRepository userDao;

    @GetMapping (value = "/getname")
    public String getName () {
        User user = new User();
        user.setUsername("闵超");
        user.setId(4L);
        user.setPassword("123456");
        userDao.save(user);
        return "你好！";
    }

}
