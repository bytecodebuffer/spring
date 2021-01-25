package singleton.bzz03;

/**
 * @author bz
 * @date 2021/1/20
 *
 * 单检查锁，不安全
 */
public class Singleton {

    private Singleton(){}

    private static  Singleton singleton = null;

    public static Singleton getInstance() {
        if(singleton == null){

            try {
                Thread.sleep(10);
            }catch (Exception e){
                e.printStackTrace();
            }

            synchronized (Singleton.class){
                singleton = new Singleton();
            }
        }
        return singleton;
    }

}
