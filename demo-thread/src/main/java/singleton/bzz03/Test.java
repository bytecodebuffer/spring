package singleton.bzz03;


/**
 * @author bz
 * @date 2021/1/25
 */
public class Test {

    public static void main(String[] args){
        for(int i = 10;i<100;i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + ":" + Singleton.getInstance().hashCode());
            },"线程"+ i ).start();
        }
    }
}
