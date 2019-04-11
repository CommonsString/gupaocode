package com.module.system.service;

import com.module.system.dto.MenuDTO;
import com.module.system.entity.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    List<MenuDTO> findByRolesForId(long id);
}
