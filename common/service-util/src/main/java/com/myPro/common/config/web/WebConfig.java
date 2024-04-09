package com.myPro.common.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.io.FileNotFoundException;

//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        String classpath = null;
//        try {
//            classpath = new File(ResourceUtils.getURL("classpath:").getPath()).getAbsolutePath();
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//
//        //配置资源映射：设置虚拟路径，访问绝对路径下资源：访问 http://localhost:9090/api/file/xxx.txt访问d:///uploadFiles/下的资源
//        registry.addResourceHandler("/api/file/**") //虚拟路径
//                .addResourceLocations("file:" + "D:\\ProgramJAVA\\Java-projects-me\\myPro-oa-parent\\service-oa\\target\\classes\\user-avatars"); //绝对路径
//    }
//
//}
