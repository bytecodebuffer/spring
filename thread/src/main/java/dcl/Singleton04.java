package dcl;

/**
 * @author bz
 * @date 2020/12/25
 */
public class Singleton04 {
    private static Singleton04 instance = new Singleton04();
    public static Singleton04 getInstance(){
        return instance;
    }

    public static void main(String [] args){
        for(int i=0;i<10;i++){
            new Thread(()->System.out.println(getInstance().hashCode())).start();
        }
    }
}
