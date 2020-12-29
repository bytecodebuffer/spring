package top.websocket.enums;

import lombok.RequiredArgsConstructor;

/**
 * @author bz
 * @date 2020/12/29
 */
@RequiredArgsConstructor
public enum NotificationTypeEnum  implements PropertyTypeEnum {
    /**
     * 校验模块
     */
    PING(200000),

    TRADING_ACCOUNT_VERIFICATION_SUCCESS(201000),

    TRADING_ACCOUNT_VERIFICATION_FAILED(201001),

    SYSTEM_MSG(1),
    TRADING_MSG(2),
    COMMENT_MSG(3),
    ACCOUNT_MSG(4);


    private final Integer code;

    @Override
    public Integer getCode() {
        return code;
    }
}
