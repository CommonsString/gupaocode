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
    public void saveContent(Content msg) {
        // 创建时间
        msg.setCreateTime(new Date());
        contentMapper.insertSelective(msg);
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
            String permission = el.getPermission();
            if("0".equals(permission)) {
                model.setTag("未审阅");
            } else {
                model.setTag("已审阅");
            }
            model.setTag(el.getPermission());
            model.setStatus(el.getIsRight());
            model.setContent(el.getContext());
            // 加入结果集
            result.add(model);
        });
        return result;
    }


}
