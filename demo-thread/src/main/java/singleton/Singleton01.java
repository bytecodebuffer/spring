package singleton;

/**
 * @author bz
 * @date 2020/12/25
 */
public class Singleton01 {
    private static Singleton01 instance = null;

    public static Singleton01 getInstance(){
        if(instance==null){
            instance = new Singleton01();
        }
        return instance;
    }

    public static void main(String [] args){
        for(int i=0;i<10;i++){
            new Thread(()->System.out.println(getInstance().hashCode())).start();
        }
    }
}
