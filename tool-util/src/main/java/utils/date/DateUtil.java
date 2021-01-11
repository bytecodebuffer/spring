package utils.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * @author bz
 * @date 2021/1/11
 */
public class DateUtil {


    /**
     * date 转换为 string
     * @param date
     * @return
     */
    public static String date2Str(Date date){
        if(date == null){
            return "";
        }
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return  formatter.format(date);
    }

    /**
     * str 转换为 date
     * @param source
     * @return
     */
    public static Date str2date(String source){
        Date date = null;
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = formatter.parse(source);
        } catch (Exception e){
            throw new RuntimeException("转换异常,时间格式: yyyy-MM-dd HH:mm:ss");
        }
        return date;
    }


    /**
     * 获取当前月的第一天
     * @return
     */
    public static Date getDateOfMonthBegin(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    /**
     * 获取当前月的最后一天
     * @return
     */
    public static Date getDateOfMonthEnd(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    public static void main(String[] args) {

        System.out.println(str2date("2020-10-10"));
    }

}
