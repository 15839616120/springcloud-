package thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

//资源类
class MySource{
    //创建map集合
    private volatile Map<String,Object> map = new HashMap<>();
    //private volatile Map<String,Object> map = new ConcurrentHashMap<>();
    //创建读写锁对象
    ReentrantReadWriteLock rwlock = new ReentrantReadWriteLock();

    //放数据
    public void put(String key,String value){
        //添加写锁
        rwlock.writeLock().lock();

        System.out.println(Thread.currentThread().getName()+"正在写数据"+key);
        //放数据
        map.put(key,value);
        System.out.println(Thread.currentThread().getName()+"写完了"+key);

        //释放锁
        rwlock.writeLock().unlock();

    }

    //取数据
    public Object get(String key){

        //添加读锁
        rwlock.readLock().lock();

        System.out.println(Thread.currentThread().getName()+"正在读数据"+key);
        Object result = map.get(key);

        System.out.println(Thread.currentThread().getName()+"读完了"+result);

        //释放锁
        rwlock.readLock().unlock();
        return result;

    }
}

/**
 * 读写锁实操
 */
public class ReentrantReadWriterLockDemo {

    public static void main(String[] args) {
        MySource mySource = new MySource();
        //创建线程放数据
        for (int i=1;i<=5;i++){
            final int finalI = i;
            new Thread(()->{
                mySource.put(finalI +"", finalI +"");
            },String.valueOf(i)).start();
        }

        //创建线程取数据
        for (int i=1;i<=5;i++){
            final int finalI = i;
            new Thread(()->{
                mySource.get(finalI +"");
            },String.valueOf(i)).start();
        }

    }


}
