package com.myPro.auth.test;

import com.myPro.auth.service.utils.PostUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestUtil {

    @Autowired
    private PostUtil postUtil;

    @Test
    public void postUtilTest(){
        System.out.println("============================================>>>>>>>>");
        postUtil.initPostCodeAll();
        System.out.println("============================================>>>>>>>>");
    }
}
