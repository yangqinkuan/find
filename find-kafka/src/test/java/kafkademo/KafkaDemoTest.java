/**
 * Copyright (C), 2018-2019,
 * FileName: KafkaDemoTest
 * Author:   Administrator
 * Date:     2019/1/5 23:42
 * Description: kafkaDemo测试
 */
package kafkademo;

import com.ice.find.kafka.KafkaApp;
import com.ice.find.kafka.kfksender.KafkaSenderImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KafkaApp.class)
public class KafkaDemoTest {
    private static Logger logger = LoggerFactory.getLogger(KafkaDemoTest.class);
    @Autowired
    private KafkaSenderImpl kafkaSenderImpl;

    @Before
    public void start(){
        System.out.println("开始测试");
    }

    @Test
    public void testKafka() throws InterruptedException {

        for (int i=0;i<5;i++){
            logger.error("发送了");
            kafkaSenderImpl.send();
            Thread.sleep(1000);
        }
    }


    @After
    public void end(){
        System.out.println("结束测试");
    }
}