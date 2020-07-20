package top.xfeng.thread.thread.test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xuefeng.wang
 * @date 2020-07-20
 */
public class AtomicDemo {

    /**
     * 使用Atomic实现线程安全
     */
    private AtomicInteger stock = new AtomicInteger(0);

    public void setStock(AtomicInteger stock) {
        this.stock = stock;
    }

    public AtomicInteger getStock() {
        return stock;
    }

    public static void main(String[] args) {
        AtomicDemo demo = new AtomicDemo();
        for (int i = 0; i < 100; i++) {
            new Thread(demo::updateStock, "user" + i).start();
        }
    }

    private void updateStock() {
        stock.getAndAdd(1);
        System.out.println("当前库存：" + stock);
    }
}
