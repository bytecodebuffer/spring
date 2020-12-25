package top.util;

import java.util.List;

/**
 * String常用方法工具
 *
 * @author lgs
 */
public class StringUtils {

    /**
     * StringList 转化为 String的公共方法
     *
     * @param stringList stringList "[1,2,3,4,5]"
     * @param length     需要返回str的长度
     * @return "1,2,3,4”
     */

    public static String stringList2String(List stringList, int length) {
        if (null == stringList || stringList.size() == 0) {
            throw new NullPointerException("参数不能为空");
        }
        if (stringList.size() < length) {
            throw new IndexOutOfBoundsException("数组长度不够");
        }
        String str = "";
        for (int i = 0; i < length; i++) {
            String toString = stringList.get(i).toString();
            if (str.equals("")) {
                str = toString + ",";
            } else if (i == length - 1) {
                str = str + toString;
            } else {
                str = str + toString + ",";
            }
        }
        return str;
    }

    /**
     * length 如果不传，则取全部的
     *
     * @param stringList
     * @return
     */
    public static String stringList2String(List stringList) {
        return stringList2String(stringList, stringList.size());
    }

    /**
     * 检查字符串是否为空或者空字符串
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str != null && str.length() > 0) {
            return false;
        }
        return true;
    }

    /**
     * 检查字符串是否不为空或者空字符串
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 检查字符串是否为空白
     *
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        if (str != null && str.trim().length() > 0) {
            return false;
        }
        return true;
    }

    /**
     * 检查字符串是否不为空白
     *
     * @param str
     * @return
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }
}
