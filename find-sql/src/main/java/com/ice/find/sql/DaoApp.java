/**
 * FileName: DaoApp
 * Author:   yangqinkuan
 * Date:     2019-1-11 11:07
 * Description:
 */

package com.ice.find.sql;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.ice.find.sql.mapper.*")

public class DaoApp {
    private static Logger logger = LoggerFactory.getLogger(DaoApp.class);

    public static void main(String[] args) {

        logger.info("启动了:");
        SpringApplication.run(DaoApp.class,args);
        logger.info("启动了:");
    }
}
