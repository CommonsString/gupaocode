package com.module.system.service;

import com.module.system.domain.Role;
import com.module.system.dto.MenuDTO;
//import com.module.system.entity.Role;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public interface MenuService {

    /**
     * 权限集合-->菜单
     * @param roles
     * @return
     */
    List<MenuDTO> findByRolesForId(Set<Role> roles);


    /**
     * buildTree
     * @param menuList
     * @return
     */
    HashMap buildTree(List<MenuDTO> menuList);

    /**
     * map -- > buildMunu
     * @param menuTree
     * @return
     */
    Object buildMenu(List<MenuDTO> menuTree);
}
