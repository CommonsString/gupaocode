package com.module.system.entity.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ContentVo {


    private Long odd;

    private String name;

    // 审阅状态
    private String status;

    // 阅读权限
    private String tag;

    // 内容
    private String content;

    // 新闻类别
    private String type;
}
