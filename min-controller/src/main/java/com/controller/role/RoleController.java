package com.controller.role;


import com.module.system.dto.RoleDTO;
import com.module.system.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@Api(tags = {"角色管理"})
public class RoleController {

    @Autowired
    private RoleService roleService;


    @ApiOperation("获取用户角色")
    @GetMapping(value = "/roles/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','ROLES_ALL','ROLES_SELECT')")
    public ResponseEntity getRoles(@PathVariable Long id){
        RoleDTO result = roleService.findRoleById(id);
        return new ResponseEntity(result, HttpStatus.OK);
    }


    /**
     * 返回全部的角色-->用户新增
     * @return
     */
    @GetMapping(value = "/roles/tree")
    @PreAuthorize("hasAnyRole('ADMIN','ROLES_ALL','USER_ALL','USER_SELECT')")
    public ResponseEntity getRoleTree(){
        return new ResponseEntity(roleService.getRoleTree(),HttpStatus.OK);
    }

}
