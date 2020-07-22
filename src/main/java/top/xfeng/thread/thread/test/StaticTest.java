package top.xfeng.thread.thread.test;

/**
 * @author xuefeng.wang
 * @date 2020-07-21
 */
public class StaticTest {

    private static int i = 10;

    private int j = 0;

    public static void main(String[] args) {
        StaticTest test1 = new StaticTest();
        StaticTest test2 = new StaticTest();
        test1.j = 1;
        test1.i = 3;
        System.out.println(test1.j);
        System.out.println(test2.i);
        System.out.println(test2.j);
    }
}
