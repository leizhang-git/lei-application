package com.yfyy.learn.designmode.strategy;

/**
 * 存放锦囊
 * @Author: zhanglei
 * @DateTime: 2022/6/6 15:26
 */
public class Context {

    //构造妙计
    private IStrategy strategy;

    public Context(IStrategy strategy) {
        this.strategy = strategy;
    }

    //使用计谋
    public void operate() {
        this.strategy.operate();
    }
}
