package com.bai.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import java.util.Locale;

@Slf4j
@Component
public class MessageUtils {

    private static MessageSource messageSource;

    public MessageUtils(MessageSource messageSource) {
        MessageUtils.messageSource = messageSource;
    }

    /**
     * 获取单个国际化翻译值
     *
     * @param msgKey 消息 Key
     */
    public static String getMsg(String msgKey) {
        try {
            return getMsg(msgKey, LocaleContextHolder.getLocale());
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return msgKey;
        }
    }

    /**
     * 获取指定地区的国际化翻译
     *
     * @param msgKey 消息 key
     * @param locale 地区
     * @return 国际化翻译
     */
    public static String getMsg(String msgKey, Locale locale) {
        try {
            return messageSource.getMessage(msgKey, null, locale);
        } catch (Exception e) {
            e.printStackTrace();
            return msgKey;
        }
    }

    /**
     * 获取指定地区的国际化翻译
     *
     * @param msgKey 消息 key
     * @param params 参数
     * @param locale 地区
     * @return 国际化翻译
     */
    public static String getMsg(String msgKey, Object[] params, Locale locale) {
        try {
            return messageSource.getMessage(msgKey, params, locale);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return msgKey;
        }
    }

}
