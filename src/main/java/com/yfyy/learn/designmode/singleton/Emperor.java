package com.yfyy.learn.designmode.singleton;

/**
 * 单例模式
 * @Author: zhanglei
 * @DateTime: 2022/6/6 17:14
 */
public class Emperor {

    // 定义一个皇帝
    private static Emperor emperor = null;

    private Emperor() {

    }

    public static Emperor getInstance() {
        // 若皇帝还没有定义，那就定义一个
        if(emperor == null) {
            emperor = new Emperor();
        }
        return emperor;
    }

    public static void emperorInfo() {
        System.out.println("我是皇帝xxx");
    }
}
