package com.ice.find;

import com.ice.find.kafka.KafkaApp;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


/**
 * Created by 012466770 ON 2018/11/15.
 */
@SpringBootApplication
public class SpringbootApplication {
    public static void main(String[] args) {
        Class<?>[] objects = new Class[2];
        objects[0] = SpringbootApplication.class;
        objects[1] = KafkaApp.class;
        SpringApplication.run(objects,args);
    }
}
