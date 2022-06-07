package com.yfyy.learn.designmode.proxy;

/**
 * @Author: zhanglei
 * @DateTime: 2022/6/6 16:57
 */
public class Wp implements KindWomen{

    private KindWomen kindWomen;

    public Wp() {
        // 默认是潘金莲的代理
        this.kindWomen = new Pjl();
    }

    public Wp(KindWomen kindWomen) {
        this.kindWomen = kindWomen;
    }

    @Override
    public void makeEyesWithMan() {
        this.kindWomen.makeEyesWithMan();
    }

    @Override
    public void happyWithMan() {
        this.kindWomen.happyWithMan();
    }
}
