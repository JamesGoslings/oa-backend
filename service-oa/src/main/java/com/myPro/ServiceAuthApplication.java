package com.myPro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan({"com.myPro.auth.mapper","com.myPro.process.mapper"})
@SpringBootApplication(scanBasePackages = {"com.myPro"})
//@EnableSwagger2WebMvc
public class ServiceAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceAuthApplication.class,args);
    }
}
