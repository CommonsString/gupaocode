package com.module.system.mapper;

import com.module.system.domain.Menu;

import java.util.LinkedHashSet;
import java.util.List;

public interface MenuMapper {
    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated
     */
    int insert(Menu record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(Menu record);

    /**
     *
     * @mbggenerated
     */
    Menu selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Menu record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Menu record);

    LinkedHashSet<Menu> findByRoleIdRetuenMenuList(Long id);

    List<Menu> findByPid(long pid);
}