package utils.id;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author bz
 * @date 2021/1/21
 */
public class UUIDStringUtils {
    public static String randomUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "").toUpperCase();
    }

    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        for(int i= 0;i<100_000;i++){
            set.add(randomUUID());
        }
        System.out.println(set.size());
    }
}
