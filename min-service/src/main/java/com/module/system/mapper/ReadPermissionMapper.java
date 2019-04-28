package com.module.system.mapper;

import com.module.system.domain.ReadPermission;

import java.util.List;

public interface ReadPermissionMapper {
    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated
     */
    int insert(ReadPermission record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(ReadPermission record);

    /**
     *
     * @mbggenerated
     */
    ReadPermission selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(ReadPermission record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(ReadPermission record);

    List<ReadPermission> selectAll();
}