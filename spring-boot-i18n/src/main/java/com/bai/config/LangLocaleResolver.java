package com.bai.config;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Component
public class LangLocaleResolver implements LocaleResolver {

    private static final String FIELD_LOCALE = "lang";

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        Locale locale = Locale.getDefault();
        String localeStr = request.getParameter(FIELD_LOCALE);
        System.out.println("localeStr:"+localeStr);
        if (StringUtils.isEmpty(localeStr)||"undefined".equals(localeStr)) {
            localeStr = "zh_CN";
        }
        boolean flag = StringUtils.isEmpty(localeStr);
        if (!flag) {
            String[] language = localeStr.split("_");
            locale = new Locale(language[0], language[1]);
            System.out.println("locale:"+locale);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
