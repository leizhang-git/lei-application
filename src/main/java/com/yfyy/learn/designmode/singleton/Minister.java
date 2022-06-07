package com.yfyy.learn.designmode.singleton;

/**
 * @Author: zhanglei
 * @DateTime: 2022/6/6 17:16
 */
public class Minister {
    public static void main(String[] args) {
        // 第一天
        Emperor emperor = Emperor.getInstance();
        Emperor.emperorInfo();

        // 第二天
        Emperor emperor1 = Emperor.getInstance();
        Emperor.emperorInfo();

        // 第三天
        Emperor emperor2 = Emperor.getInstance();
        Emperor.emperorInfo();
    }
}
