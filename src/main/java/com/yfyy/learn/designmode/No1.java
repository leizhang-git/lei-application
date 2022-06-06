package com.yfyy.learn.designmode;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 单例模式：目的一般是处理资源访问的冲突
 * 或者从业务角度考虑，有些业务适合设计为单例类：配置类、全局流水号生成器等
 * @Author: zhanglei
 * @DateTime: 2022/6/6 13:35
 */
public class No1 {

    /**
     * 实现单例模式需要关注的点：
     * 1.构造函数需要是private访问权限
     * 2.考虑对象创建时的线程安全问题
     * 3.考虑是否支持延迟加载
     * 4.考虑getInstance()性能
     */

    /**
     * 饿汉式
     * 类加载时，instance静态实例就已经创建初始化（线程安全）
     */
    static class IdGenerator {
        private AtomicLong id = new AtomicLong(0);
        private static final IdGenerator instance = new IdGenerator();

        private IdGenerator() {}

        public static IdGenerator getInstance() {
            return instance;
        }

        public long getId() {
            return id.incrementAndGet();
        }
    }

    /**
     * 懒汉式
     * 支持延迟加载！
     */
    static class IdGenerator1 {
        private AtomicLong id = new AtomicLong(0);
        private static IdGenerator1 instance;
        private IdGenerator1() {}

        //注意：此处指令重排序在jdk1.5之后已经修复（把对象 new 操作和初始化操作设计为原子操作，就自然能禁止重排序）
        public static IdGenerator1 getInstance() {
            if (instance == null) {
                // 此处为类级别的锁
                synchronized(IdGenerator1.class) {
                    if (instance == null) {
                        instance = new IdGenerator1();
                    }
                }
            }
            return instance;
        }

        public long getId() {
            return id.incrementAndGet();
        }
    }

    /**
     * 采用Java静态内部类
     * 类似与饿汉式，但是又能做到延迟加载
     */
    static class IdGenerator2 {
        private AtomicLong id = new AtomicLong(0);
        private IdGenerator2() {}

        /**
         * SingletonHolder 是一个静态内部类，当外部类 IdGenerator 被加载的时候，
         * 并不会创建 SingletonHolder 实例对象。
         * 只有当调用 getInstance() 方法时，SingletonHolder 才会被加载，这个时候才会创建 instance
         */
        private static class SingletonHolder{
            private static final IdGenerator2 instance = new IdGenerator2();
        }

        public static IdGenerator2 getInstance() {
            return SingletonHolder.instance;
        }

        public long getId() {
            return id.incrementAndGet();
        }
    }

    /**
     * 单例模式缺点：
     * 1.OOP特性支持不友好
     * 2.隐藏类之间的依赖关系
     * 3.代码扩展性一般
     * 4.可测试性一般
     * 5.单例不支持有参数的构造函数
     */
}
