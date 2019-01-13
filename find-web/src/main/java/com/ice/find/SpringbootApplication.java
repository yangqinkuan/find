package com.ice.find;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Created by 012466770 ON 2018/11/15.
 */
@SpringBootApplication
//@MapperScan(basePackages = {"com.ice.find.sql.mapper.user"})
public class SpringbootApplication {
    public static void main(String[] args) {
/*        Class<?>[] objects = new Class[6];
        objects[0] = SpringbootApplication.class;
        objects[1] = DaoApp.class;
        objects[2] = KafkaApp.class;
        objects[3] = LoginApp.class;
        objects[4] = MailApp.class;
        objects[5] = RegistryApp.class;*/
        SpringApplication.run(SpringbootApplication.class,args);
    }
}
