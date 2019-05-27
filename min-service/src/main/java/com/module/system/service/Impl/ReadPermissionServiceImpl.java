package com.module.system.service.Impl;

import com.exception.EntityExistException;
import com.module.system.domain.ContentType;
import com.module.system.domain.ReadPermission;
import com.module.system.mapper.ReadPermissionMapper;
import com.module.system.service.ReadPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ReadPermissionServiceImpl implements ReadPermissionService {


    @Autowired
    private ReadPermissionMapper readPermissionMapper;

    @Override
    public List<ReadPermission> getRead(String name) {
        return readPermissionMapper.findByNameLike(name);
    }

    @Override
    public List<ReadPermission> findAll() {
        return readPermissionMapper.selectAll();
    }

    @Override
    public void addRead(ReadPermission read) {
        // 查重
        ReadPermission temp = readPermissionMapper.findByName(read.getName());
        if(temp != null) {
            throw new EntityExistException(ReadPermission.class, "name");
        }
        readPermissionMapper.insertSelective(read);
    }

    @Override
    public void updateRead(ReadPermission read) {
        readPermissionMapper.updateByPrimaryKeySelective(read);
    }

    @Override
    public void deleteForId(Long id) {
        readPermissionMapper.deleteByPrimaryKey(id);
    }
}
