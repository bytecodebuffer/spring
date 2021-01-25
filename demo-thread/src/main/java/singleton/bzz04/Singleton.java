package singleton.bzz04;

/**
 * @author bz
 * @date 2021/1/20
 *
 * 双检查锁，线程安全
 */
public class Singleton {

    private Singleton(){}

    public static Singleton singleton = null;

    public static Singleton getInstance(){
        if(singleton == null){
            synchronized (Singleton.class){
                if(singleton == null){
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
