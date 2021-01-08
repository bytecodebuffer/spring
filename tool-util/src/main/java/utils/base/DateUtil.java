package utils.base;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author bz
 * @date 2021/1/8
 */
public class DateUtil {

    /**
     * 获取当前时间  yyyy-mm-dd HH:mm:ss
     */
    public static String now(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(new Date());
    }

    /**
     * string 类型转换为 date
     * yyyy-mm-dd HH:mm:ss
     * @param source
     * @return
     */
    public static Date str2date(String source){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try{
            date = formatter.parse(source);
        }catch (Exception e){
            e.printStackTrace();
        }
        return date;
    }

    /**
     * date 类型格式化输出
     * @param date
     * @return
     */
    public static String date2str(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return  formatter.format(date);
    }

}
