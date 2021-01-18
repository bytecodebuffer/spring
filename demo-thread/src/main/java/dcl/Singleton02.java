package dcl;

/**
 * @author bz
 * @date 2020/12/25
 */
public class Singleton02 {

    private static  Singleton02 instance = null;

    public static synchronized Singleton02 getInstance(){
        if (instance == null) {
            try {
                Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }
            instance = new Singleton02();
        }
        return instance;
    }

    public static void main(String [] args){
        for(int i=0;i<10;i++){
            new Thread(()->System.out.println(getInstance().hashCode())).start();
        }
    }
}
