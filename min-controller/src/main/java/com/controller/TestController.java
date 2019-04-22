package com.controller;


import com.module.system.domain.Role;
import com.module.system.dto.MenuDTO;
import com.module.system.mapper.ContentMapper;
import com.module.system.mapper.MenuMapper;
import com.module.system.mapper.PermissionMapper;
import com.module.system.mapper.RoleMapper;
import com.module.system.service.MenuService;
import com.module.system.service.RoleService;
import com.module.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("test")
public class TestController {


    @Autowired
    private ContentMapper contentMapper;


    @Autowired
    private PermissionMapper permissionMapper;


    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuMapper menuMapper;

    @GetMapping (value = "/getname")
    public Object getName () {
//        return menuRepository.findByRoles_IdOrderBySortAsc(1L);
        return null;
    }



}
