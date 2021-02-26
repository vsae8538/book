package com.github.vsae;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@MapperScan(basePackages = "com.github.vsae" , annotationClass = Mapper.class)
@ComponentScan({"com.github.vsae"})
@SpringBootApplication
public class BookManageServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookManageServerApplication.class, args);
    }

}
