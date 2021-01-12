package utils.base;

import java.math.BigDecimal;

/**
 * @author bz
 * @date 2021/1/12
 */
public class Double2BigDecimal {

    /**
     * 使用double 会出现的问题
     */
    public static void doubleCalc(){
        Double price1 =  0.1;
        Double price2 = 0.01;
        System.out.println(price1 - price2);

        Double price3 = 0.09;
        System.out.println( price3.equals(price1-price2) );
    }

    /**
     *
     */
    public static void bigDecimalCalc(){
        BigDecimal price1 = BigDecimal.valueOf(0.1);
        BigDecimal price2 = BigDecimal.valueOf(0.01);
        System.out.println(price1.subtract(price2));

        BigDecimal price3 = BigDecimal.valueOf(0.09);
        System.out.println( price3.compareTo(price1.subtract(price2)));

    }

    public static void main(String[] args) {
        doubleCalc();
        bigDecimalCalc();


    }
}
