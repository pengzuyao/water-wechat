package com.pzy.study;

import com.pzy.study.utils.SpringUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-09-01
 */

@SpringBootApplication
@MapperScan(basePackages = {"com.pzy.study.mapper"})
@ComponentScan(basePackages={"com.pzy.study" , "org.n3r.idworker"})
public class WaterWechatApplicationContext {

    public static void main(String[] args) {
        SpringApplication.run(WaterWechatApplicationContext.class ,args);
    }

    @Bean
    public SpringUtil springUtil(){
        return new SpringUtil();
    }
}
