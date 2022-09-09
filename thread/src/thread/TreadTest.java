package thread;


//第一步，创建资源类，定义属性和操作方法
class Share{
    //初始值
    private int number = 0;
    //+1方法
    public synchronized void incr() throws InterruptedException {
        //第二步 判断  干活  通知
        if(number!=0){
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName()+"：："+number);
        this.notifyAll();
    }
    //-1方法
    public synchronized void decr() throws InterruptedException {
        //第二步 判断  干活  通知
        if(number!=1){
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName()+"：："+number);
        this.notifyAll();
    }
}
public class TreadTest {
    public static void main(String[] args) throws Exception {
        Share share = new Share();
        new Thread(()->{
            for (int i=0;i<=10;i++){
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"AA").start();
        new Thread(()->{
            for (int i=0;i<=10;i++){
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"BB").start();
        new Thread(()->{
            for (int i=0;i<=10;i++){
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"CC").start();
        new Thread(()->{
            for (int i=0;i<=10;i++){
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"DD").start();
    }
}