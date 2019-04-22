package com.module.system.service;

import com.module.system.domain.Content;
import com.module.system.entity.vo.ContentVo;

import java.util.List;

public interface ContentService {
    void saveContent(Content msg);

    List<ContentVo> findMsgLimit();
}
