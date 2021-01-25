package singleton.bzz02;

/**
 * @author bz
 * @date 2021/1/20
 *
 * 线程安全
 * 缺点：实例化一次才需要使用锁，其他情况下，每次都要获取锁，浪费资源
 *
 */
public class Singleton {

    private Singleton(){}

    private static Singleton singleton = null;

    public static synchronized Singleton getInstance(){
        if(singleton == null){
            singleton = new Singleton();
        }
        return singleton;
    }
}
