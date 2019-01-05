/**
 * Copyright (C), 2018-2019,
 * FileName: KafkaDemoTest
 * Author:   Administrator
 * Date:     2019/1/5 23:42
 * Description: kafkaDemo测试
 */
package kafka;

import com.ice.find.SpringbootApplication;
import com.ice.find.kafka.kfksender.KafkaSenderImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootApplication.class)
public class KafkaDemoTest {

    @Autowired
    private KafkaSenderImpl kafkaSenderImpl;

    @Before
    public void start(){
        System.out.println("开始测试");
    }

    @Test
    public void testKafka() throws InterruptedException {
        for (int i=0;i<5;i++){
            kafkaSenderImpl.send();
            Thread.sleep(1000);
        }
    }


    @After
    public void end(){
        System.out.println("结束测试");
    }
}