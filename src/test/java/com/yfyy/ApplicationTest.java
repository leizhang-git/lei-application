package com.yfyy;

import cn.hutool.core.bean.BeanUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
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

    @Test
    public void test() {
        List<Integer> list = null;
        List<Integer> integers = BeanUtil.copyToList(list, Integer.class);
        System.out.println(integers.toString());
    }
}
