package thread;

import java.util.concurrent.CountDownLatch;

/**
 * 演示CountDownLatch
 * 场景：5个同学离开教室后，值班同学才可以关门
 *案例
 */
public class CountDownLatchDemo {
    //错误的示例
 /*   public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 1;i<=5;i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"  号同学离开了教室");
            },String.valueOf(i)).start();
        }
        System.out.println(Thread.currentThread().getName()+"班长锁门");
    }*/
    //正确的示例
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 1;i<=5;i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"  号同学离开了教室");
                //递减锁存器的记数，如果记数到达零，则释放所有等待的线程
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        //使当前线程在锁存器倒计数至零之前一直等待
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"班长锁门");
    }



}
