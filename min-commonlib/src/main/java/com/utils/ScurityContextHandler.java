package com.utils;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 获取当前JwtUser
 */
public class ScurityContextHandler {

    public static UserDetails get() {
        UserDetails details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(details == null) {
            throw new RuntimeException("userInfo is expire!");
        }
        return details;
    }

}
