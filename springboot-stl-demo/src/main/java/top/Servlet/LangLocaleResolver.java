package top.Servlet;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;


/**
 * 自定义语言解析器
 *
 * @author lgs
 */
@Component
public class LangLocaleResolver implements LocaleResolver {

    private static final String FIELD_LOCALE = "locale";

    /**
     * Resolve the current locale via the given request.
     * Can return a default locale as fallback in any case.
     *
     * @param request the request to resolve the locale for
     * @return the current locale (never {@code null})
     */
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        Locale locale = Locale.getDefault();
        String localeStr = request.getHeader(FIELD_LOCALE);
        if (StringUtils.isEmpty(localeStr)) {
            localeStr = "zh_CN";
        }
        boolean flag = StringUtils.isEmpty(localeStr);
        if (!flag) {
            String[] language = localeStr.split("_");
            locale = new Locale(language[0], language[1]);
        }
        return locale;
    }

    /**
     * Set the current locale to the given one.
     *
     * @param request  the request to be used for locale modification
     * @param response the response to be used for locale modification
     * @param locale   the new locale, or {@code null} to clear the locale
     * @throws UnsupportedOperationException if the LocaleResolver
     *                                       implementation does not support dynamic changing of the locale
     */
    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}


