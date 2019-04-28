package com.module.system.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@ToString
@Setter
@Getter
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

    private Long type;

}