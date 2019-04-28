package com.module.system.service.Impl;

import com.module.system.domain.ContentType;
import com.module.system.domain.ReadPermission;
import com.module.system.mapper.ContentTypeMapper;
import com.module.system.mapper.ReadPermissionMapper;
import com.module.system.service.MsgGetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
public class MsgGetServiceImpl implements MsgGetService {

    @Autowired
    private ReadPermissionMapper readPermissionMapper;

    @Autowired
    private ContentTypeMapper contentTypeMapper;


    @Override
    public List<Object> findByReadAndType() {
        List<Object> result = new ArrayList();
        // 新闻类型
        List<ContentType> con = this.contentTypeMapper.selectAll();
        // 阅读权限
        List<ReadPermission> per = this.readPermissionMapper.selectAll();
        return result;
    }




}
