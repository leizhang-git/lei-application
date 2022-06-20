package com.yfyy;

import cn.hutool.core.lang.Assert;
import com.yfyy.util.ClassUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Author: zhanglei
 * @DateTime: 2022/6/20 13:49
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ClassTest {

    @Test
    public void test() {
        String path = "com.yfyy.domain.User";
        Class<?> aClass = ClassUtils.getClass(path);
        Method[] methods = ClassUtils.getMethods(path);
        Field[] fields = ClassUtils.getFields(path);
        Constructor[] constructors = ClassUtils.getConstructors(path);
        Assert.notNull(aClass);
        Assert.notNull(methods);
        Assert.notNull(fields);
        Assert.notNull(constructors);
    }
}
