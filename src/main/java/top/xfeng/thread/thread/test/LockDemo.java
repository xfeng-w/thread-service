package top.xfeng.thread.thread.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xuefeng.wang
 * @date 2020-07-21
 */
public class LockDemo {
    private static final Lock lock = new ReentrantLock();
    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }

    private int stock = 0;

    public static void main(String[] args) {
        LockDemo demo = new LockDemo();
        for (int i = 0; i < 1000; i++) {
            new Thread(demo::updateStock, "user" + i).start();
        }
    }


    private void updateStock() {
        try {
            lock.lock();
            stock++;
            System.out.println("用户:" + Thread.currentThread() + "修改库存，" + "当前库存：" + stock);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
