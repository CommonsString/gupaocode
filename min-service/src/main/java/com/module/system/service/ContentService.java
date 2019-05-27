package com.module.system.service;

import com.module.system.domain.Content;
import com.module.system.entity.vo.ContentVo;
import com.module.system.entity.vo.UpVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ContentService {
    void updateContent(ContentVo msg);

    List<ContentVo> findMsgLimit();

    void updateMainIndexMsg(Long id);

    void saveContent(ContentVo content);

    List<ContentVo> findMsglike(Content content);

    List<ContentVo> findMsgAll();

    void updateMsgIsRight(Long id);

    void deleteForId(Long id);

    List<UpVo> inportMsg(MultipartFile file);
}
