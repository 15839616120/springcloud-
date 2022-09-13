package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class CallableDemo  implements Callable<String> {

    private String string;
    //构造
    public CallableDemo(String string) {
        this.string = string;
    }

    //线程主方法
    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName());
        Thread.sleep(5000);
        return string;
    }


   /*public static void main(String[] args) throws Exception {
        Callable<String> callable = new CallableDemo("my callable is ok!");
        FutureTask<String> task = new FutureTask<String>(callable);
        //创建线程

        new Thread(task,"aaa").start();
        System.out.println(Thread.currentThread().getName()+"11");
        //调用get()方法阻塞主线程
        String str = task.get();
        System.out.println("hello :"+ str);
        System.out.println(Thread.currentThread().getName()+"22");


    }*/


    public static void main(String[] args) throws Exception {
        //Callable<String> callable = new CallableDemo("my callable is ok!");
        FutureTask<String> task = new FutureTask<String>(()->{
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(5000);
            return "callable";
        });
        //创建线程

        new Thread(task,"aaa").start();
        System.out.println(Thread.currentThread().getName()+"11");
        //调用get()方法阻塞主线程
        String str = task.get();
        System.out.println("hello :"+ str);
        System.out.println(Thread.currentThread().getName()+"22");

    }


}
