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

    public static String format(Double source){
        if(source == null){
            return "0.00";
        }
        DecimalFormat decimalFormat  = new DecimalFormat("#0.00");
        return decimalFormat.format (source);
    }

    public static String format(String source){
        if(StringUtil.isBlank(source)){
            return "0.00";
        }
        DecimalFormat decimalFormat  = new DecimalFormat("#0.00");
        return decimalFormat.format (parseDouble(source));
    }

    public static Double parseDouble(String source){
        if(StringUtil.isBlank(source) || StringUtil.isBlank(source)){
            return 0.00;
        }
        return Double.parseDouble(source);
    }
}
