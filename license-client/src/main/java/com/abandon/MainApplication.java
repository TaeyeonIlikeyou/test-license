package com.abandon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author guozx
 * @date 2022/12/13
 */
@SpringBootApplication
@ServletComponentScan
// @PropertySource({"license-config.properties"}) // 加载额外的配置
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}
