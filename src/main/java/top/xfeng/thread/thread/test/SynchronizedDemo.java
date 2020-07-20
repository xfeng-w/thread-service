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

    private int stock = 0;

    public static void main(String[] args) {
        SynchronizedDemo demo = new SynchronizedDemo();
        for (int i = 0; i < 100; i++) {
            new Thread(demo::updateStock, "user" + i).start();
        }
    }

    /**
     * 使用synchronized关键字实现线程安全
     * 线程安全的情况下stock会递增到100
     */
    private synchronized void updateStock() {
        stock++;
        System.out.println("用户:" + Thread.currentThread() + "修改库存，" + "当前库存：" + stock);
    }
}
