package thread;

public class ThreadDemo {

    public void one() throws InterruptedException{
        synchronized (this) {
            boolean flag = true;

            while (flag) {

                for(int i = 1; i <= 99;i += 2){
                    System.out.println(Thread.currentThread().getName() + "--" + i);

                    if(i==99){
                        flag = false;
                        this.notify();
                        break;
                    }
                    /*this.notify();
                    this.wait();*/
                }
            }
        }
    }

    public void two() throws InterruptedException{
        synchronized (this) {
            boolean flag = true;

            while (flag) {

                for(int i = 2; i <= 100;i += 2){
                    System.out.println(Thread.currentThread().getName() + "--" + i);

                    if(i==100){
                        flag = false;
                        this.notify();
                        break;
                    }
                   /* this.notify();
                    this.wait();*/
                }
            }
        }
    }



    public static void main(String[] args) throws Exception {
        ThreadDemo sumThread = new ThreadDemo();

        new Thread(()->{
            try {
                sumThread.one();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();


        new Thread(()->{
            try {
                sumThread.two();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}