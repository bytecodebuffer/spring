package top.websocket.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import top.websocket.enums.NotificationTypeEnum;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author bz
 * @date 2020/9/21
 */
@Data
public class MessageObject<T> implements Serializable{

    @ApiModelProperty("消息接收者用户id，0表示广播")
    public Long userId;

    @ApiModelProperty("消息体，参考文档")
    private T data;

    @ApiModelProperty("通知类型")
    private Integer type;

    @ApiModelProperty("消息发送时间, 13位时间戳")
    private LocalDateTime sendTime;

    public MessageObject() {
        this.userId = 0L;
        this.type = 0;
        this.sendTime = LocalDateTime.now();
    }

    public static  MessageObject<NotificationInfoEntity<String>> info(String msg) {
        NotificationInfoEntity<String> infoEntity = new NotificationInfoEntity<>(msg);
        return data(infoEntity, NotificationTypeEnum.PING);
    }

    /**
     * 发送广播消息
     * @param data 数据包
     * @param typeEnum 消息类型
     * @param <T> 数据包对象
     * @return row
     */
    public static <T> MessageObject<T> data(T data,
                                            NotificationTypeEnum typeEnum) {
        return data(data, typeEnum, 0L);
    }


    /**
     * 发送指定消息
     * @param data 数据包
     * @param typeEnum 消息类型
     * @param userId 用户id
     * @param <T> 数据包对象
     * @return row
     */
    public static <T> MessageObject<T> data(T data,
                                            NotificationTypeEnum typeEnum,
                                            Long userId) {
        MessageObject<T> messageObject = new MessageObject<>();
        messageObject.setUserId(userId);
        messageObject.setData(data);
        messageObject.setType(typeEnum.getCode());
        return messageObject;
    }
}
