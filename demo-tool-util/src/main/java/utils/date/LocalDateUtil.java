package utils.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author bz
 * @date 2021/1/21
 */
public class LocalDateUtil {

    public static LocalDateTime str2datetime(String source) {
        DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(source,formatter);
    }

    public static String datetime2str(LocalDateTime source){
        DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return formatter.format(source);
    }

    public static Integer isToday(LocalDateTime source){
        DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String date = datetime2str(source);
        LocalDate now = LocalDate.now();
        return now.compareTo(LocalDate.parse(date,formatter));
    }

    public static void main(String[] args) {
        System.out.println(str2datetime("2020-10-11 10:10:10"));
        System.out.println(datetime2str(LocalDateTime.now()));
        System.out.println(isToday(str2datetime("2021-01-22 10:10:10")));
    }
}
