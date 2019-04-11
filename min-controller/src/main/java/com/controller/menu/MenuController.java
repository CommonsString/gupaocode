package com.controller.menu;

import com.module.system.dto.MenuDTO;
import com.module.system.entity.Role;
import com.module.system.entity.User;
import com.module.system.service.MenuService;
import com.module.system.service.RoleService;
import com.module.system.service.UserService;
import com.utils.SecurityContextHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("menu")
@Api(tags = {"菜单管理"})
public class MenuController {


    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;

    @PostMapping(value = "/build")
    @ApiOperation("构建菜单, 返回前端菜单视图")
    public ResponseEntity buildViewMenu() {
        // 获取当前用户
        UserDetails userDetails = SecurityContextHandler.get();
        User user = userService.findUserByName(userDetails.getUsername());
        // 根据权限, 获取菜单列表
        Set<Role> roles = null;
        List<MenuDTO> menuList = roleService.findByRolesForId(1L);
        // 获取
        return null;
    }


}
