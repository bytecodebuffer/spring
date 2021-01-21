package singleton;

/**
 * @author bz
 * @date 2020/12/25
 */
public class Singleton03 {
    private static volatile Singleton03 instance = null;

    public static  Singleton03 getInstance(){
        if (instance == null) {
            synchronized(Singleton03.class) {
                if(instance==null){
                    instance = new Singleton03();
                }
            }
        }
        return instance;
    }

}
