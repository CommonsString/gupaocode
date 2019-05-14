package com.module.system.mapper;

import com.module.system.domain.Permission;
import com.module.system.domain.Role;
import com.module.system.dto.PermissionDTO;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface PermissionMapper {
    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated
     */
    int insert(Permission record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(Permission record);

    /**
     *
     * @mbggenerated
     */
    Permission selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Permission record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Permission record);

    Set<Permission> selectByIdReturnList(Long id);

    Set<Permission> findByPid(long pid);

    List<Permission> findByNameAll(String name);

    Permission findByName(String name);
}