package top.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * 获取语言国际化工具类
 *
 * @author lgs
 */

@Component
public class MessageUtils {

    private static Logger logger = LoggerFactory.getLogger(MessageUtils.class);

    private static MessageSource messageSource;

    public MessageUtils(MessageSource messageSource) {
        MessageUtils.messageSource = messageSource;
    }

    /**
     * 获取单个国际化翻译值
     */
    public static String getMsg(String msgKey) {
        try {
            return getMsg(msgKey, LocaleContextHolder.getLocale());
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            return msgKey;
        }
    }

    /**
     * 获取指定地区的国际化翻译
     *
     * @param msgKey 消息key
     * @param locale 地区
     * @return
     */
    public static String getMsg(String msgKey, Locale locale) {
        try {
            return messageSource.getMessage(msgKey, null, locale);
        } catch (Exception e) {
            e.printStackTrace();
            return msgKey;
        }
    }
}


