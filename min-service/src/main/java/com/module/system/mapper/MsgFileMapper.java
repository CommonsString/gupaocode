package com.module.system.mapper;

import com.module.system.domain.MsgFile;

public interface MsgFileMapper {
    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated
     */
    int insert(MsgFile record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(MsgFile record);

    /**
     *
     * @mbggenerated
     */
    MsgFile selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(MsgFile record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(MsgFile record);
}