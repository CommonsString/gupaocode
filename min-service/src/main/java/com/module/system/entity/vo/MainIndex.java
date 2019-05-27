package com.module.system.entity.vo;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MainIndex {

    private Long isRight;
    private Long NotIsRight;
    // 今日采集
    private Long newGet;
    // 类型数量
    private Long typeCount;

}
