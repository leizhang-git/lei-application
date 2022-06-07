package com.yfyy.learn.designmode.singleton;

/**
 * 单例模式：在构造函数中多加了一个构造函数，访问权限是private
 * @Author: zhanglei
 * @DateTime: 2022/6/6 17:19
 */
public class SingletonPattern {
    private static final SingletonPattern singletonPattern = new SingletonPattern();

    // 限制住不能直接产生一个实例
    private SingletonPattern() {

    }

    public synchronized static SingletonPattern getInstance() {
        return singletonPattern;
    }
}
