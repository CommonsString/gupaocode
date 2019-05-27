package com.module.system.service.Impl;

import com.exception.EntityExistException;
import com.module.system.domain.ContentType;
import com.module.system.mapper.ContentTypeMapper;
import com.module.system.service.ContentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ContentTypeServiceImpl implements ContentTypeService {

    @Autowired
    private ContentTypeMapper contentTypeMapper;

    @Override
    public void addType(ContentType type) {
        // 查重
        ContentType temp = contentTypeMapper.findByName(type.getName());
        if(temp != null) {
            throw new EntityExistException(ContentType.class, "name");
        }
        contentTypeMapper.insertSelective(type);
    }

    @Override
    public List<ContentType> findAll() {
        return contentTypeMapper.selectAll();
    }

    @Override
    public List<ContentType> getType(String name) {
        return contentTypeMapper.findByNameLike(name);
    }

    @Override
    public void deleteForId(Long id) {
        contentTypeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateType(ContentType type) {
        contentTypeMapper.updateByPrimaryKeySelective(type);
    }
}
