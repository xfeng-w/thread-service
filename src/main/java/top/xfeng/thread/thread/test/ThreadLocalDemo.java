package top.xfeng.thread.thread.test;

/**
 * @author xuefeng.wang
 * @date 2020-07-20
 */
public class ThreadLocalDemo {

    /**
     * 线程独享
     * ThreadLocal每开一个线程都是线程内的变量
     */
    ThreadLocal<Integer> stock = ThreadLocal.withInitial(() -> 0);

    public void setStock(ThreadLocal<Integer> stock) {
        this.stock = stock;
    }

    public ThreadLocal<Integer> getStock() {
        return stock;
    }

    public static void main(String[] args) {
        ThreadLocalDemo demo = new ThreadLocalDemo();
        for (int i = 0; i < 100; i++) {
            new Thread(demo::updateStock, "user" + i).start();
        }
    }

    private void updateStock() {
        this.stock.set(stock.get() + 1);
        System.out.println("当前库存：" + stock.get());
    }
}
