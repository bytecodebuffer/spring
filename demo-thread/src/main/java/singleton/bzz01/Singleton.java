package singleton.bzz01;

/**
 * @author bz
 * @date 2021/1/20
 *
 * 线程安全
 * 静态全局单例，不管是否使用，都会创建一个单例对象
 */
public class Singleton {

    private Singleton(){}

    private static final Singleton singleton = new Singleton();

    public static Singleton getInstance(){
        return singleton;
    }
}
