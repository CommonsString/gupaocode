package com.module.system.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@Getter
@Setter
public class ContentDTO implements Serializable {

    private Long id;

    private String title;

    private String permission;

    private String content;

    private String isRight;

}
