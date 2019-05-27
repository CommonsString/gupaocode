package com.module.system.entity.vo;

import com.utils.excel.IsNeeded;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class UpVo {

    private Long odd;

    @IsNeeded
    private String title;

    @IsNeeded
    private String content;
}
