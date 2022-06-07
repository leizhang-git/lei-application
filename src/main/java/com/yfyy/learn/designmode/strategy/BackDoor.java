package com.yfyy.learn.designmode.strategy;

/**
 * 妙计1.找乔国老帮忙，使孙权不能杀刘备
 * @Author: zhanglei
 * @DateTime: 2022/6/6 15:22
 */
public class BackDoor implements IStrategy{
    @Override
    public void operate() {
        System.out.println("乔国老帮忙");
    }
}
