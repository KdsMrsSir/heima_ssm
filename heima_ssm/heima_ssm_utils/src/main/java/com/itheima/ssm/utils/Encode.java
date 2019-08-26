package com.itheima.ssm.utils;/*
作用：  
 */

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Encode {


    public static String encodePassword(String password) {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        String encode = bCryptPasswordEncoder.encode(password);

        return encode;

    }

    public static void main(String[] args) {
        String s = encodePassword("123");
        System.out.println(s);
    }



}
