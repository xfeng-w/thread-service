package top.xfeng.thread.entity;

import lombok.Data;

import java.util.concurrent.locks.Lock;

/**
 * @author xuefeng.wang
 * @date 2020-07-02
 */
@Data
public class User implements Runnable {
    /**
     * 用户名
     */
    private String name;

    /**
     * 账户
     */
    private MyCount myCount;

    /**
     * 操作的金额
     */
    private int cash;

    /**
     * 执行操作的锁
     */
    private Lock myLock;

    User(Lock myLock, String name, MyCount myCount, int cash) {
        this.myLock = myLock;
        this.name = name;
        this.myCount = myCount;
        this.cash = cash;
    }

    @Override
    public void run() {
        //获取锁
        this.myLock.lock();
        //执行现金业务
        System.out.println(name + "正在操作" + this.myCount + "账户，金额为" + this.cash + ",当前金额为" + this.myCount.getCash());
        this.myCount.setCash(this.myCount.getCash() + this.cash);
        System.out.println(name + "操作" + this.myCount + "账户成功，金额为" + this.cash + ",当前金额为" + this.myCount.getCash());
        //释放锁
        this.myLock.unlock();
    }
}
