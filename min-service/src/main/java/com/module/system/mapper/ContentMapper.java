package com.module.system.mapper;

import com.module.system.domain.Content;

import java.util.List;

public interface ContentMapper {
    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated
     */
    int insert(Content record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(Content record);

    /**
     *
     * @mbggenerated
     */
    Content selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Content record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeyWithBLOBs(Content record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Content record);

    List<Content> findByIsRightDesc();
}