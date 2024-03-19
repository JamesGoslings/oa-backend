package com.myPro;

import com.myPro.common.jwt.JwtHelper;
import org.junit.jupiter.api.Test;

public class commonTest {

    @Test
    public void testToken(){
        String token = JwtHelper.createToken(6L, "testName");
        System.out.println(token);
        Long userId = JwtHelper.getUserId(token);
        System.out.println(userId);
        String username = JwtHelper.getUsername(token);
        System.out.println(username);
    }
}
