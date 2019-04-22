package com.module.system.domain;

import java.util.Date;

public class Permission {
    /**
     * ID
     */
    private Long id;

    /**
     * 别名
     */
    private String alias;

    /**
     * 创建日期
     */
    private Date createTime;

    /**
     * 名称
     */
    private String name;

    /**
     * 上级权限
     */
    private Integer pid;

    /**
     * ID
     * @return id ID
     */
    public Long getId() {
        return id;
    }

    /**
     * ID
     * @param id ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 别名
     * @return alias 别名
     */
    public String getAlias() {
        return alias;
    }

    /**
     * 别名
     * @param alias 别名
     */
    public void setAlias(String alias) {
        this.alias = alias == null ? null : alias.trim();
    }

    /**
     * 创建日期
     * @return create_time 创建日期
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建日期
     * @param createTime 创建日期
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 名称
     * @return name 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 名称
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 上级权限
     * @return pid 上级权限
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * 上级权限
     * @param pid 上级权限
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }
}