package com.module.system.service;

import com.module.security.units.JwtUser;
import com.module.system.domain.User;
import com.module.system.dto.UserDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

@CacheConfig(cacheNames = "user")
public interface UserService {

    User findUserByName(String username);
    /**
     * get
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    UserDTO findById(long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    UserDTO create(User resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(User resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);

    /**
     * findByName
     * @param userName
     * @return
     */
    @Cacheable(keyGenerator = "keyGenerator")
    User findByName(String userName);

    /**
     * 修改密码
     * @param jwtUser
     * @param encryptPassword
     */
    @CacheEvict(allEntries = true)
    void updatePass(JwtUser jwtUser, String encryptPassword);

    /**
     * 修改头像
     * @param jwtUser
     * @param url
     */
    @CacheEvict(allEntries = true)
    void updateAvatar(JwtUser jwtUser, String url);

    /**
     * 修改邮箱
     * @param jwtUser
     * @param email
     */
    @CacheEvict(allEntries = true)
    void updateEmail(JwtUser jwtUser, String email);
}
