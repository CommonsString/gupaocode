package com.module.system.domain;

import lombok.ToString;

import java.util.Date;

@ToString
public class Content {
    /**
     * 
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 
     */
    private Date createTime;

    /**
     * 附件
     */
    private Long fileId;

    /**
     * 阅读权限
     */
    private String permission;

    /**
     * 是否审阅
     */
    private String isRight;

    /**
     * 内容
     */
    private String context;

    /**
     * 
     * @return id 
     */
    public Long getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 标题
     * @return title 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 标题
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 
     * @return create_time 
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 
     * @param createTime 
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 附件
     * @return file_id 附件
     */
    public Long getFileId() {
        return fileId;
    }

    /**
     * 附件
     * @param fileId 附件
     */
    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    /**
     * 阅读权限
     * @return permission 阅读权限
     */
    public String getPermission() {
        return permission;
    }

    /**
     * 阅读权限
     * @param permission 阅读权限
     */
    public void setPermission(String permission) {
        this.permission = permission == null ? null : permission.trim();
    }

    /**
     * 是否审阅
     * @return is_right 是否审阅
     */
    public String getIsRight() {
        return isRight;
    }

    /**
     * 是否审阅
     * @param isRight 是否审阅
     */
    public void setIsRight(String isRight) {
        this.isRight = isRight == null ? null : isRight.trim();
    }

    /**
     * 内容
     * @return context 内容
     */
    public String getContext() {
        return context;
    }

    /**
     * 内容
     * @param context 内容
     */
    public void setContext(String context) {
        this.context = context == null ? null : context.trim();
    }
}