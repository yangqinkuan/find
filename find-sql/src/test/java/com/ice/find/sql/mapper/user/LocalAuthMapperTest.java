package com.ice.find.sql.mapper.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class LocalAuthMapperTest {
    @Autowired
    private LocalAuthMapper authMapper;

    @Test
    public void selectByEmail() {
        authMapper.selectByEmail("male_2@163.com");
        System.out.println("dd");
    }
}