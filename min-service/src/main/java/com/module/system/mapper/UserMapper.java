package com.module.system.mapper;

import com.module.system.domain.User;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface UserMapper {
    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated
     */
    int insert(User record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(User record);

    /**
     *
     * @mbggenerated
     */
    User selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(User record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(User record);

    User findByEmail(String username);

    User findByUsername(String username);


    void updatePassword(@Param("id") Long id, @Param("password") String md5Password,
                        @Param("date") Date date);
}