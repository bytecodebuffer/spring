import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author bz
 * @date 2021/2/18
 */
public class RpcFramework {

    /**
     * 暴漏服务
     * @param service
     * @param port
     * @throws IOException
     */
    public static void export(final Object service,int port) throws IOException{
        if(service == null){
            throw new IllegalArgumentException("service is null");
        }
        if(port <=0 || port >=65535 ){
            throw new IllegalArgumentException("port is illegal : " + port);
        }
        System.out.println("export service :" + service.getClass().getName() + " on port :" + port);
        ServerSocket server = new ServerSocket(port);
        while(true){
            try{
                final Socket socket = server.accept();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            try {
                                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                                try {
                                    String methodName = inputStream.readUTF();
                                    Class<?>[] paramTypes = (Class<?>[]) inputStream.readObject();
                                    Object[] arguments = (Object[]) inputStream.readObject();
                                    ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                                    try {
                                        Method method = service.getClass().getMethod(methodName, paramTypes);
                                        Object result = method.invoke(service, arguments);
                                        outputStream.writeObject(result);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    } finally {
                                        outputStream.close();
                                    }
                                } finally {
                                    inputStream.close();
                                }
                            }finally {
                                socket.close();
                            }
                        }catch (Exception e){
                           e.printStackTrace();
                        }
                    }
                }).start();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static <T> T refer(final Class<T> interfaceClass,final String host,final int port){
        if( interfaceClass == null ){
            throw new IllegalArgumentException("interfaceClass is null");
        }
        if(!interfaceClass.isInterface()){
            throw new IllegalArgumentException("the" + interfaceClass.getClass().getName()+"must be interface class");
        }
        if(host == null || host.length() == 0){
            throw new IllegalArgumentException("host is null");
        }
        if(port <=0 || port >= 65535){
            throw new IllegalArgumentException("port is illegal : " + port);
        }
        System.out.println("get remote service " + interfaceClass.getName() + " from server " + host + ":" + port);
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                Socket socket = new Socket(host,port);
                try {
                    ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                    try {
                        outputStream.writeUTF(method.getName());
                        outputStream.writeObject(method.getParameterTypes());
                        outputStream.writeObject(args);
                        ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

                        try {
                            Object result = inputStream.readObject();
                            if (result instanceof Throwable) {
                                throw (Throwable) result;
                            }
                            return result;
                        } finally {
                            inputStream.close();
                        }
                    } finally {
                        outputStream.close();
                    }
                }finally {
                    socket.close();
                }
            }
        });

    }
}
