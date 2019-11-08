package com.zeus;

import com.zeus.common.utils.SpringUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 启动类
 * @author:fusheng
 * @date:2019-03-07
 * @ver:1.0
 **/
@SpringBootApplication
@EnableScheduling
@Import(SpringUtils.class)
@MapperScan("com.zeus.dao")
public class OutputInputApplication {

    public static void main(String[] args) {
        SpringApplication.run(OutputInputApplication.class, args);
    }

}
