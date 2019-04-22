package com.module.system.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Menu {
    /**
     * ID
     */
    private Long id;

    /**
     * 创建日期
     */
    private Date createTime;

    /**
     * 是否外链
     */
    private Boolean iFrame;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 组件
     */
    private String component;

    /**
     * 上级菜单ID
     */
    private Long pid;

    /**
     * 排序
     */
    private Long sort;

    /**
     * 图标
     */
    private String icon;

    /**
     * 链接地址
     */
    private String path;


}