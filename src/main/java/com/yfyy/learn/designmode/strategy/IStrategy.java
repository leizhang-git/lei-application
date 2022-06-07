package com.yfyy.learn.designmode.strategy;

/**
 * 策略模式(策略接口) 高内聚、低耦合 可扩展性！可以继续增加下去
 * 诸葛亮给赵云的锦囊妙计
 * @Author: zhanglei
 * @DateTime: 2022/6/6 15:21
 */
public interface IStrategy {

    /**
     * 策略算法
     */
    void operate();
}
