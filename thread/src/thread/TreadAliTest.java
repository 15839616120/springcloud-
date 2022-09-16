package thread;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//第一步，创建资源类
class ShareDate{
    private Integer number=1;
    Lock lock= new ReentrantLock(true);
    public void AddNumber() throws InterruptedException {
        lock.lock();
        try {
                System.out.println(Thread.currentThread().getName()+"::"+number++);
            if (number>100){
                return;
            }
        }finally {
            lock.unlock();
        }
    }
}


public class TreadAliTest {
    public static void main(String[] args) throws Exception {
        //初始值
        ShareDate shareDate = new ShareDate();
      /*  Long start = System.currentTimeMillis();
        System.out.println("start"+Long.toString(start));


        //方法一
        new Thread(()->{
            try {

                for (int i=1;i<=100;i++){
                    try {
                        shareDate.AddNumber();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"AA").start();


        new Thread(()->{
            try {
                for (int i=1;i<=100;i++){
                    try {
                        shareDate.AddNumber();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"BB").start();
        System.out.println("end:--------------------------------------"+ Long.toString(System.currentTimeMillis()-start));*/
    //方法二

      ExecutorService threadPool = Executors.newFixedThreadPool(2);
        for (int i=1;i<=100;i++){
        CompletableFuture.runAsync(()->{
                try {
                    shareDate.AddNumber();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        },threadPool);
        }

        threadPool.shutdown();

    }

}
