package top.xfeng.thread.thread.test;

/**
 * @author xuefeng.wang
 * @date 2020-07-20
 */
public class SynchronizedDemo {

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }

    private volatile int stock = 10;

    public static void main(String[] args) {
        SynchronizedDemo demo = new SynchronizedDemo();
        while (demo.getStock() > 0) {
            new Thread(demo::updateStock,"user1").start();
            new Thread(demo::updateStock,"user2").start();
            new Thread(demo::updateStock,"user3").start();
        }

    }

    private synchronized void updateStock() {
        System.out.println("用户:"+Thread.currentThread()+"将要修改库存，"+"当前库存："+stock);
        if (stock > 0) {
            stock--;
        }
    }
}
