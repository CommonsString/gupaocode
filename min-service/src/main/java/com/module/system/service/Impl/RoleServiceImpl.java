package com.module.system.service.Impl;

import com.module.system.domain.Role;
//import com.module.system.entity.Role;
import com.module.system.mapper.RoleMapper;
import com.module.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
public class RoleServiceImpl implements RoleService {


    @Autowired
    private RoleMapper roleMapper;


    /**
     * userid-->RoleList
     * @param id
     * @return
     */
    @Override
    public Set<Role> findRoleListById(Long id) {
//        return roleDao.findByUsers_Id(id);
        return roleMapper.findByIdReturnList(id);
    }

}
