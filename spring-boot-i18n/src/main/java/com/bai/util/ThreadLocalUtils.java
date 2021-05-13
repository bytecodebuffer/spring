package com.bai.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.Locale;

@Slf4j
public class ThreadLocalUtils {

    private static final ThreadLocal<Locale> LOCALE_LOCAL = new ThreadLocal<>();

    private static Locale getLocale(String locale) {
        if (StringUtils.isEmpty(locale)) {
            log.warn("locale is empty, use default local SIMPLIFIED_CHINESE");
            return Locale.SIMPLIFIED_CHINESE;
        }
        switch (locale) {
            case "zh_TW":
                return Locale.TRADITIONAL_CHINESE;
            case "zh_CN":
                return Locale.SIMPLIFIED_CHINESE;
            case "en_US":
                return Locale.US;
            default:
                log.warn("{} not found, use default local SIMPLIFIED_CHINESE", locale);
                break;
        }
        return Locale.SIMPLIFIED_CHINESE;
    }


    public static void saveLocale(String locale) {
        LOCALE_LOCAL.set(getLocale(locale));
    }

}
