package com.ice.find.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Created by 012466770 ON 2018/11/15.
 */
@SpringBootApplication
public class KafkaApp {
    private static Logger logger = LoggerFactory.getLogger(KafkaApp.class);
    public static void main(String[] args) {

        logger.info("启动了:");
        SpringApplication.run(KafkaApp.class,args);
    }
}
