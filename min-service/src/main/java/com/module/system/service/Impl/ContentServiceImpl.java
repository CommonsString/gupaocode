package com.module.system.service.Impl;

import com.module.system.domain.Content;
import com.module.system.entity.vo.ContentVo;
import com.module.system.mapper.ContentMapper;
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


    @Autowired
    private ContentMapper contentMapper;

    @Override
    public void updateContent(ContentVo msg) {
        Content content = new Content();
        // id
        content.setId(msg.getOdd());
        // context
        content.setContext(msg.getContent());
        // permission
        content.setPermission(msg.getTag());
        // title
        content.setTitle(msg.getName());
        // isRight
        String isRight = msg.getStatus();
        if ("未审阅".trim().equals(isRight)) {
            content.setIsRight("0");
        } else if("已审阅".trim().equals(isRight)) {
            content.setIsRight("1");
        }
        contentMapper.updateByPrimaryKeySelective(content);
    }

    @Override
    public List<ContentVo> findMsgLimit() {
        // 查询
        List<Content> tmp_result = contentMapper.findByIsRightDesc();
        List<ContentVo> result = new ArrayList<ContentVo>();
        tmp_result.forEach(el -> {
            ContentVo model = new ContentVo();
            model.setOdd(el.getId());
            model.setName(el.getTitle());
            // 转换
            String isRight = el.getIsRight();
            if ("0".equals(isRight)) {
                model.setStatus("未审阅");
            } else {
                model.setStatus("已审阅");
            }
            model.setTag(el.getPermission());
            model.setContent(el.getContext());
            // 加入结果集
            result.add(model);
        });
        return result;
    }


    /**
     * 主页更新状态
     *
     * @param id
     */
    @Override
    public void updateMainIndexMsg(Long id) {

        Content content = new Content();
        content.setId(id);
        content.setIsRight("1");
        contentMapper.updateByPrimaryKeySelective(content);
    }

    /**
     * 保存新闻
     * @param content
     */
    @Override
    public void saveContent(ContentVo content) {
        Content contentDomain = new Content();
        contentDomain.setTitle(content.getName());
        contentDomain.setContext(content.getContent());
        contentDomain.setPermission(content.getTag());
        contentDomain.setCreateTime(new Date());
        contentMapper.insertSelective(contentDomain);
    }


}
