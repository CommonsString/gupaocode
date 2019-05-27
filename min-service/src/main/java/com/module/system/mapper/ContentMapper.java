package com.module.system.mapper;

import com.module.system.domain.Content;
import com.module.system.entity.vo.ContentVo;
import org.springframework.data.repository.query.Param;

import java.util.Date;
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

    void updateIsRightStatus(Long id);

    List<Content> findByIsRightMsg(Content content);

    List<Content> findAll();

    Long countIsRight();

    Long countNotIsRight();

    Content findByTitle(String name);

    Long countMsgNow(Date createTime);
}