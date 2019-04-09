package com.module.security.units;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * 自定义用户安全类
 */
@Getter
@AllArgsConstructor
@ToString
public class JwtUser implements UserDetails {

    @JsonIgnore
    private final Long id;

    private final String username;

    @JsonIgnore
    private final String password;

    private final String avatar;

    private final String email;

    @JsonIgnore
    private final Collection<? extends GrantedAuthority> authorities;

    private final boolean enabled;

    private Timestamp createTime;

    @JsonIgnore
    private final Date lastPasswordResetDate;

    // 账号是否过期
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 账号锁定
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 密码是否过期
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    // 激活
    @Override
    public boolean isEnabled() {
        return enabled;
    }

    // 获取权限
    public Collection getRoles() {
        return authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
    }
}
