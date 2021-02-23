package utils.base;



/**
 * @author bz
 * @date 2021/1/8
 */
public class StringUtil {

    public static boolean isBlank( String string) {
        if (!isEmpty(string)) {
            for (int i = 0; i < string.length(); ++i) {
                if (!Character.isWhitespace(string.charAt(i))) {
                    return false;
                }
            }

        }
        return true;
    }

    public static boolean isNotBlank( String str) {
        return !isBlank(str);
    }

    public static boolean isEmpty( String string) {
        return string == null || string.isEmpty();
    }

    public static boolean isNotEmpty( String string) {
        return !isEmpty(string);
    }

    public static String truncate(String string, int maxLength) {
        return string.length() > maxLength ? string.substring(0, maxLength) : string;
    }
}
