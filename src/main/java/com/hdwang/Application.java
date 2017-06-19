package com.hdwang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by hdwang on 2017/6/5.
 */
//@EntityScan(value="com.hdwang.entity")
@SpringBootApplication
public class Application {

    /**
     * main function
     * @param args params
     */
    public static void main(String[] args){
       SpringApplication.run(Application.class,args);
    }
}
