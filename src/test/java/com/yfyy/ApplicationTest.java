package com.yfyy;

import cn.hutool.core.bean.BeanUtil;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author: zhang lei
 * @Date: 2022/4/21 14:36
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ApplicationTest {

    private static final Logger log = LoggerFactory.getLogger(ApplicationTest.class);

    @Autowired
    private StringEncryptor stringEncryptor;

    @Test
    public void test() {
        List<Integer> list = null;
        List<Integer> integers = BeanUtil.copyToList(list, Integer.class);
        System.out.println(integers.toString());
    }

    @Test
    public void testPwd() {
        log.info(stringEncryptor.encrypt("xx"));
        log.info(stringEncryptor.decrypt("xx"));
    }
}
