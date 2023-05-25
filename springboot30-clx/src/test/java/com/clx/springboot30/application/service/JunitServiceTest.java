package com.clx.springboot30.application.service;

import com.clx.springboot30.Springboot30ClxApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 注意：
 * idea设置：Settings > Build,Execution,Deployment > Build Tools > Gradle > build and run > “Run tests using:”
 * 把 gradle(default) 替换成IntelliJ IDEA
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Springboot30ClxApplication.class)
public class JunitServiceTest {
    @Autowired
    JunitService junitService;

    @Test
    public void testGetMessage1() {
        Assert.assertEquals("test", junitService.getMessage("test"));
    }

    @Test
    public void testGetMessage2() {
        Assert.assertEquals("test", junitService.getMessage("testetss"));
    }
}
