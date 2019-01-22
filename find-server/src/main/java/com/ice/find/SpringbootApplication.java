package com.ice.find;


import com.ice.find.mail.MailApp;
import com.ice.find.registry.RegistryApp;
import com.ice.find.sql.DaoApp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Created by 012466770 ON 2018/11/15.
 */
@SpringBootApplication
//@MapperScan(basePackages = {"com.ice.find.sql.mapper.user"})
public class SpringbootApplication {
    public static void main(String[] args) {
        Class<?>[] objects = new Class[4];
        objects[0] = SpringbootApplication.class;
        objects[1] = DaoApp.class;
        //objects[2] = KafkaApp.class;
        //objects[3] = LoginApp.class;
        objects[2] = MailApp.class;
        objects[3] = RegistryApp.class;
        SpringApplication.run(SpringbootApplication.class,args);
    }
}
