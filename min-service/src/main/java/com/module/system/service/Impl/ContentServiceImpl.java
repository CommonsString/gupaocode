package com.module.system.service.Impl;

import com.module.system.domain.Content;
import com.module.system.entity.vo.ContentVo;
import com.module.system.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
public class ContentServiceImpl implements ContentService {





    @Override
    public void saveContent(Content msg) {

    }

    @Override
    public List<ContentVo> findMsgLimit() {
        return null;
    }

/*    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private ContentMapper contentMapper;

    *//**
     * 保存新闻
      * @param msg
     *//*
    @Override
    public void saveContent(Content msg) {
        // 创建时间
        msg.setCreateTime(new Date());
        contentRepository.save(msg);
    }

    *//**
     * 获取最新10条信息
     * @return
     *//*
    @Override
    public List<ContentVo> findMsgLimit() {
        // 查询
        List<Content> tmp_result = this.contentRepository.findByIsRightDesc();
        // 转成VO
//        List<ContentDTO> result = tmp_result.stream().map(contentMapper::toDto).collect(Collectors.toList());
        List<ContentVo> result = new ArrayList<ContentVo>();
        tmp_result.forEach(el -> {
            ContentVo model = new ContentVo();
            model.setOdd(el.getId());
            model.setName(el.getTitle());
            model.setTag(el.getPermission());
            model.setStatus(el.getIsRight());
            // 加入结果集
            result.add(model);
        });
        return result;
    }*/


}
