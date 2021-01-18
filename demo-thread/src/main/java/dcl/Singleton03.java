package dcl;

/**
 * @author bz
 * @date 2020/12/25
 */
public class Singleton03 {
    private static volatile Singleton03 instance = null;

    public static  Singleton03 getInstance(){
        if (instance == null) {
            try {
                Thread.sleep(10);
            }catch (Exception e){
                e.printStackTrace();
            }
            synchronized(Singleton03.class) {
                if(instance==null){
                    instance = new Singleton03();
                }
            }
        }
        return instance;
    }

    public static void main(String [] args){
        for(int i=0;i<10;i++){
            new Thread(()->System.out.println(getInstance().hashCode())).start();
        }
    }
}
