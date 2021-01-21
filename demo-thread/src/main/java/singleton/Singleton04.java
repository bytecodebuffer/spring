package singleton;

/**
 * @author bz
 * @date 2020/12/25
 */
public class Singleton04 {
    private static Singleton04 instance = new Singleton04();
    public static Singleton04 getInstance(){
        return instance;
    }
}
