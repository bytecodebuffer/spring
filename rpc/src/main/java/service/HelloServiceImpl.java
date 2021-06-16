package service;

/**
 * @author bz
 * @date 2021/2/18
 */
public class HelloServiceImpl implements HelloService{

    @Override
    public String call() {
        return "Hello World";
    }
}
