package top.websocket.enums;

import java.lang.reflect.Method;

/**
 * @author bz
 * @date 2020/12/29
 */
public interface PropertyTypeEnum {
    /**
     * 获取枚举对应的值
     *
     * @return code
     */
    Integer getCode();


    /**
     * 通过code获取枚举元素
     * @param code code值
     * @param enumType 枚举类型
     * @return 枚举元素
     */
    @SuppressWarnings("unchecked")
    static <T extends Enum<T>> T valueOf(Integer code, Class<T> enumType){

        Enum<T>[] enumConstants = enumType.getEnumConstants();
        try {
            Method getCode = enumType.getMethod("getCode");
            for (Enum<T> subCodeEnum : enumConstants){
                Integer invoke = (Integer) getCode.invoke(subCodeEnum);
                if (code.equals(invoke)){
                    return (T) subCodeEnum;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("未找到" + code + "对应的枚举类");
        }
        throw new RuntimeException("未找到" + code + "对应的枚举类");
    }
}
