package com.module.system.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

@Data
public class RoleDTO implements Serializable {

    private Long id;

    private String name;

    private String remark;

    private Set<PermissionDTO> permissions;

    private Set<MenuDTO> menus;

    private Timestamp createTime;
}
