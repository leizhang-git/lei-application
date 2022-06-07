package com.yfyy.learn.designmode.strategy;

/**
 * 妙计3.孙夫人断后，挡住残兵
 * @Author: zhanglei
 * @DateTime: 2022/6/6 15:23
 */
public class BlockEnemy implements IStrategy{
    @Override
    public void operate() {
        System.out.println("孙夫人断后");
    }
}
