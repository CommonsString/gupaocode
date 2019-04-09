package com;

import com.module.system.entity.User;
import com.module.system.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.GetMapping;


@SpringBootTest
@RunWith(SpringRunner.class)
public class Main {


    @Autowired
    private UserRepository userDao;

    @Test
    public void getName () {
        User user = new User();
        user.setUsername("闵超");
        user.setPassword("123456");
        userDao.save(user);
    }


}
