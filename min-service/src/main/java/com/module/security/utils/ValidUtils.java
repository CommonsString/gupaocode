package com.module.security.utils;

public class ValidUtils {


    /**
     * 验证邮箱格式
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        if (email == null){
            return false;
        }
        String valid = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        return email.matches(valid);
    }




}
