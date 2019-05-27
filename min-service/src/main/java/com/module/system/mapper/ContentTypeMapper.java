package com.module.system.mapper;

import com.module.system.domain.ContentType;

import java.util.List;

public interface ContentTypeMapper {
    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated
     */
    int insert(ContentType record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(ContentType record);

    /**
     *
     * @mbggenerated
     */
    ContentType selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(ContentType record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(ContentType record);

    List<ContentType> selectAll();

    ContentType findByName(String name);

    List<ContentType> findByNameLike(String name);

    Long countType();
}