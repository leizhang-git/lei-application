package com.yfyy.learn.designmode.strategy;

/**
 * 执行
 * @Author: zhanglei
 * @DateTime: 2022/6/6 15:27
 */
public class Execute {
    public static void main(String[] args) {
        Context context;
        System.out.println("妙计1-----------");
        context = new Context(new BackDoor());
        //执行
        context.operate();

        System.out.println("妙计2-----------");
        context = new Context(new GivenGreenLight());
        //执行
        context.operate();

        System.out.println("妙计3-----------");
        context = new Context(new BlockEnemy());
        //执行
        context.operate();
    }
}
