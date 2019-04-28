package com.module.system.service;

import com.module.system.domain.Content;
import com.module.system.entity.vo.ContentVo;

import java.util.List;

public interface ContentService {
    void updateContent(ContentVo msg);

    List<ContentVo> findMsgLimit();

    void updateMainIndexMsg(Long id);

    void saveContent(ContentVo content);
}
