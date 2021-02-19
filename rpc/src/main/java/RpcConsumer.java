import service.HelloService;

/**
 * @author bz
 * @date 2021/2/18
 */
public class RpcConsumer {
    public static void main(String[] args) throws Exception{
        HelloService service = RpcFramework.refer(HelloService.class,"127.0.0.1",1234);
        for(int  i = 0;i< Integer.MAX_VALUE;i++){
            System.out.println(service.call());
            Thread.sleep(1000);
        }
    }
}
