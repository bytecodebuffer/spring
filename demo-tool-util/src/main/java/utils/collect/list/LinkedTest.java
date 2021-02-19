package utils.collect.list;

import java.util.LinkedList;
import java.util.List;

/**
 * @author bz
 * @date 2021/2/19
 */
public class LinkedTest {

    public static void main(String[] args) {

        LinkedList list = new LinkedList<>();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        list.forEach(e->System.out.println(e));

    }
}
