package thread;

import java.util.concurrent.*;

/**
 * CompletableFuture线程测试类
 * 四个核心构造方法
 *
 */
public class TreadPoolTest {
    public static void main(String[] args) throws Exception {

        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        CompletableFuture<Integer> exceptionally = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t" + ".....come in");
            int result = ThreadLocalRandom.current().nextInt(10);

            if (result > 1) {
                int i = 10 / 0;
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(".....1秒后出结果：" + result);

            return result;
        }, threadPool).whenComplete((r, e) -> {
            if (e == null) {
                System.out.println(".....计算完成，更新系统updatevalue：" + r);
            }
        }).exceptionally(e -> {
            System.out.println("异常发生：" + e.getCause() + "\t" + e.getMessage());
            return 333;
        });
        System.out.println(Thread.currentThread().getName()+"线程先去忙其他任务了");
        threadPool.shutdown();
        System.out.println(Thread.currentThread().getName()+"主线程结束");
        System.out.println(exceptionally.get());



        //无返回值 无指定线程池
        /*CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(()->{
            System.out.println(Thread.currentThread().getName()+"\t"+".....come in");

            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(".....task is over");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
        System.out.println(voidCompletableFuture.get());*/


        //无返回值 指定线程池
      /* ExecutorService threadPool = Executors.newFixedThreadPool(3);
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(()->{
            System.out.println(Thread.currentThread().getName()+"\t"+".....come in");
        },threadPool);

        try {
            TimeUnit.SECONDS.sleep(1);
            System.out.println(".....task is over");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(voidCompletableFuture.get());
        //关闭线程池
        threadPool.shutdown();*/


        //有返回值 无指定线程池
       /* CompletableFuture<String> objectCompletableFuture = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName()+"\t"+".....come in");
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(".....task is over");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "无参 supplyAsync";
        });
        System.out.println(objectCompletableFuture.get());*/

        //有返回值 指定线程池
       /* ExecutorService threadPool = Executors.newFixedThreadPool(3);
        CompletableFuture<Object> objectCompletableFuture = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName()+"\t"+".....come in");
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(".....task is over");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "有参 supplyAsync";
        },threadPool);
        System.out.println(objectCompletableFuture.get());
        threadPool.shutdown();
        */


    }

    private static void feature() throws Exception {
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t" + ".....come in");
            int result = ThreadLocalRandom.current().nextInt(10);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(".....1秒后出结果：" + result);

            return result;
        });
        System.out.println(Thread.currentThread().getName()+"线程先去忙其他任务了");
        System.out.println(integerCompletableFuture.get());
    }
}
