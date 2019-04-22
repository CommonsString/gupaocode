package com.module.system.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Setter
@Getter
public class Role {
    /**
     * ID
     */
    private Long id;

    /**
     * 创建日期
     */
    private Date createTime;

    /**
     * 名称
     */
    private String name;

    /**
     * 备注
     */
    private String remark;

    private Set<Permission> permissions;

    private Set<Menu> menus;
}