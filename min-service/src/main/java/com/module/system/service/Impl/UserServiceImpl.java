package com.module.system.service.Impl;

import com.exception.BadRequestException;
import com.exception.EntityExistException;
import com.exception.EntityNotFoundException;
import com.module.security.units.JwtUser;
import com.module.security.utils.JwtTokenUtil;
import com.module.security.utils.ValidUtils;
import com.module.system.domain.User;
import com.module.system.dto.UserDTO;
import com.module.system.dto.translation.UserTranslation;
import com.module.system.mapper.UserMapper;
import com.module.system.repository.UserRepository;
import com.module.system.service.UserService;
import com.utils.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserTranslation userTranslation;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    /**
     * 根据姓名查找用户
     *
     * @param username
     * @return
     */
    @Override
    public User findUserByName(String username) {
        if ("".equals(username) || username == null) {
            throw new RuntimeException("username valid!");
        }
        User user = null;
        // 邮箱
        if (ValidUtils.isEmail(username)) {
            user = userMapper.findByEmail(username);
        } else {
            user = userMapper.findByUsername(username);
        }
        // 用户null
        if (user == null) {
            throw new RuntimeException("user null！");
        }
        return user;
    }



    @Override
    public UserDTO findById(long id) {
        Optional<User> user = userRepository.findById(id);
        ValidationUtil.isNull(user,"User","id",id);
        return userTranslation.toDto(user.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserDTO create(User resources) {

        if(userRepository.findByUsername(resources.getUsername())!=null){
            throw new EntityExistException(User.class,"username",resources.getUsername());
        }

        if(userRepository.findByEmail(resources.getEmail())!=null){
            throw new EntityExistException(User.class,"email",resources.getEmail());
        }

        if(resources.getRoles() == null || resources.getRoles().size() == 0){
            throw new BadRequestException("角色不能为空");
        }

        // 默认密码 123456，此密码是 MD5加密后的字符
        resources.setPassword("14e1b600b1fd579f47433b88e8d85291");
        resources.setAvatar("https://i.loli.net/2018/12/06/5c08894d8de21.jpg");
        return userTranslation.toDto(userRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(User resources) {

        Optional<User> userOptional = userRepository.findById(resources.getId());
        ValidationUtil.isNull(userOptional,"User","id",resources.getId());

        User user = userOptional.get();

        User user1 = userRepository.findByUsername(user.getUsername());
        User user2 = userRepository.findByEmail(user.getEmail());

        if(resources.getRoles() == null || resources.getRoles().size() == 0){
            throw new BadRequestException("角色不能为空");
        }

        if(user1 !=null&&!user.getId().equals(user1.getId())){
            throw new EntityExistException(User.class,"username",resources.getUsername());
        }

        if(user2!=null&&!user.getId().equals(user2.getId())){
            throw new EntityExistException(User.class,"email",resources.getEmail());
        }

        user.setUsername(resources.getUsername());
        user.setEmail(resources.getEmail());
        user.setEnabled(resources.getEnabled());
        user.setRoles(resources.getRoles());

        userRepository.save(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByName(String userName) {
        User user = null;
        if(ValidationUtil.isEmail(userName)){
            user = userRepository.findByEmail(userName);
        } else {
            user = userRepository.findByUsername(userName);
        }

        if (user == null) {
            throw new EntityNotFoundException(User.class, "name", userName);
        } else {
            return user;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePass(JwtUser jwtUser, String pass) {
        userRepository.updatePass(jwtUser.getId(),pass,new Date());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateAvatar(JwtUser jwtUser, String url) {
        userRepository.updateAvatar(jwtUser.getId(),url);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateEmail(JwtUser jwtUser, String email) {
        userRepository.updateEmail(jwtUser.getId(),email);
    }
}
