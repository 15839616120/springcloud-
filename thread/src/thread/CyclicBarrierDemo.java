package thread;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 循环阻塞
 * 集齐七颗龙珠可以召唤神龙
 */
public class CyclicBarrierDemo {

    //创建目标值
    private static final int NUMBER=7;

    public static void main(String[] args) {
        //创建CyclicBarrier，触发到NUMBER值后，执行里面的内容
        CyclicBarrier cyclicBarrier = new CyclicBarrier(NUMBER,()->{
            System.out.println("******集齐7颗龙珠可以召唤神龙");
        });

        //集齐七颗龙珠的过程
        for (int i=1;i<=7;i++){
            new Thread(()->{
                try {
                    System.out.println(Thread.currentThread().getName()+"颗龙珠收集到了");
                    cyclicBarrier.await();

                    System.out.println(Thread.currentThread().getName()+"完事");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }

}
