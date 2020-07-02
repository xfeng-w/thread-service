package top.xfeng.thread.entity;

import lombok.Data;

/**
 * @author xuefeng.wang
 * @date 2020-07-02
 */
@Data
public class MyCount {
    /**
     * 账户
     */
    private String oid;

    /**
     * 余额
     */
    private int cash;

    MyCount(String oid,int cash){
        this.oid=oid;
        this.cash=cash;
    }
}
