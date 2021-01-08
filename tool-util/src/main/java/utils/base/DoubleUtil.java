package utils.base;


import java.text.DecimalFormat;

/**
 * @author bz
 * @date 2021/1/7
 *
 * 该工具类用于将 Double 类型的数据保留固定的小数位
 *
 */
public class DoubleUtil {

    public static String formatSource(Double source){
        if(source == null){
            return "";
        }
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        return decimalFormat.format(source);
    }

    public static String format(String source){
        if(StringUtil.isBlank(source)){
            return "0.00";
        }
        DecimalFormat decimalFormat  = new DecimalFormat("#0.00");
        return decimalFormat.format (Double.valueOf(source));
    }
}
