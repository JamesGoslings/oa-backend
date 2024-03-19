package com.myPro.common.jwt;

import cn.hutool.core.convert.NumberWithFormat;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class JwtHelper {

    private final static String KEY = "123456";

    public static String createToken(Long userId,String username){
        Map<String, Object> map = new HashMap<String, Object>() {
            private static final long serialVersionUID = 1L;
            {
                put("userId", Integer.parseInt(userId.toString()));
                put("username", username);
                put("expirationTime", System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 15);
            }
        };
        return JWTUtil.createToken(map,KEY.getBytes(StandardCharsets.UTF_8));
    }

    public static Long getUserId(String token){
        JWT jwt = JWTUtil.parseToken(token);
        return ((NumberWithFormat)jwt.getPayload("userId")).longValue();
    }

    public static String getUsername(String token){
        JWT jwt = JWTUtil.parseToken(token);
        return (String) jwt.getPayload("username");
    }
}
