import service.HelloService;
import service.HelloServiceImpl;

/**
 * @author bz
 * @date 2021/2/18
 */
public class RpcProvider {
    public static void main(String[] args) throws Exception{
        HelloService service = new HelloServiceImpl();
        RpcFramework.export(service,1234);
    }
}
