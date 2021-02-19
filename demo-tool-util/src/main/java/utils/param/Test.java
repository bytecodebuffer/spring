package utils.param;


import java.util.Arrays;
import java.util.List;

/**
 * @author bz
 * @date 2021/2/6
 *
 * 值传递与引用传递的区别
 */
public class Test {

    public static void main(String[] args) {
        List<User> userList = Arrays.asList(
                new User("zhang"),
                new User("lis"),
                new User("wang")
        );


        userList.stream().forEach(e->System.out.println(e));

    }
}
