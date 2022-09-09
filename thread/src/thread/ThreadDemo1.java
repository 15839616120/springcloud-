package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 需求：
 * 启动三个线程
 * AA打印五次  BB打印10次  CC打印15次
 *
 * 打印10轮
 */


/**
 * 资源类
 */
class ShareResource {
    //标识符
    private volatile int flag = 1;

    //创建lock锁对象
    ReentrantLock reentrantLock = new ReentrantLock();
    //创建三个condition
    Condition condition1 = reentrantLock.newCondition();
    Condition condition2 = reentrantLock.newCondition();
    Condition condition3 = reentrantLock.newCondition();

    //打印五次
    public void print5(int loop) {
        reentrantLock.lock();
        try {
            //判断
            while (flag != 1) {
                //等待
                condition1.await();
            }
            //干活
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "::" + i + "轮数" + loop);
            }
            //通知
            flag = 2;
            condition2.signalAll();//通知B线程
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }

    //打印十次
    public void print10(int loop) {
        reentrantLock.lock();
        try {
            //判断
            while (flag != 2) {
                //等待
                condition2.await();
            }
            //干活
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + "::" + i + "轮数" + loop);
            }
            //通知
            flag = 3;
            condition3.signalAll();//通知C线程
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //释放锁
            reentrantLock.unlock();
        }
    }

    //打印十次
    public void print15(int loop) {
        reentrantLock.lock();
        try {
            //判断
            while (flag != 3) {
                //等待
                condition3.await();
            }
            //干活
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + "::" + i + "轮数" + loop);
            }
            //通知
            flag = 1;
            condition1.signalAll();//通知A线程
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }
}

/**
 * 线程间通信
 */
public class ThreadDemo1 {

    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(() -> {
            for (int loop = 1; loop <= 5; loop++){
                shareResource.print5(loop);
            }
        }, "AA").start();

        new Thread(() -> {
            for (int loop = 1; loop <= 10; loop++){
                shareResource.print10(loop);
            }
        }, "BB").start();

        new Thread(() -> {
            for (int loop = 1; loop <= 15; loop++){
                shareResource.print15(loop);
            }
        }, "CC").start();
    }
}
