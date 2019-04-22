package com.module.system.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.constraints.Pattern;
import java.sql.Timestamp;
import java.util.Date;

@Setter
@Getter
public class User {
    /**
     * ID
     */
    private Long id;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 创建日期
     */
    private Timestamp createTime;

    /**
     * 邮箱
     */
    @Pattern(regexp = "([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}",message = "格式错误")
    private String email;

    /**
     * 状态：1启用、0禁用
     */
    private Long enabled;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户名
     */
    private String username;

    /**
     * 最后修改密码的日期
     */
    private Timestamp lastPasswordResetTime;

    public @interface Update {}

}