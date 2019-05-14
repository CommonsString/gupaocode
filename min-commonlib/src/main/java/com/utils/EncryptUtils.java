package com.utils;


import org.springframework.util.DigestUtils;

/**
 * 加密工具
 */
public class EncryptUtils {

    /**
     * MD5
     * @param password
     * @return
     */
    public static String encryptPassword(String password){
        return  DigestUtils.md5DigestAsHex(password.getBytes());
    }

    public static void main(String[] args) {
        System.out.println(encryptPassword("wanmcdmmaa123"));

    }

}
