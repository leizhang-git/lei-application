package com.yfyy.learn.designmode.strategy;

/**
 * 妙计2.求吴国太开个绿灯
 * @Author: zhanglei
 * @DateTime: 2022/6/6 15:22
 */
public class GivenGreenLight implements IStrategy{
    @Override
    public void operate() {
        System.out.println("吴国太开个绿灯");
    }
}
