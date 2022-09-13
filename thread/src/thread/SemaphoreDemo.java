package thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号灯
 * 六辆汽车，三个停车位
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        //设置许可数量
        Semaphore semaphore = new Semaphore(3);
        //模拟六辆汽车
        for (int i=1;i<=6;i++){
            new Thread(()->{
                try {
                    //获取授权
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"抢占了车位");

                    //设置停车时间
                    TimeUnit.SECONDS.sleep(5);

                    System.out.println(Thread.currentThread().getName()+"离开了车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            }).start();
        }
    }
}
