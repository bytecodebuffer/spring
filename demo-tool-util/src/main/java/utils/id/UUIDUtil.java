package utils.id;


import java.util.UUID;

/**
 * @author bz
 * @date 2021/1/21
 */
public class UUIDUtil {
    public static String randomUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "").toUpperCase();
    }
}
