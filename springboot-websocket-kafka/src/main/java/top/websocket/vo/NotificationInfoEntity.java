package top.websocket.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author bz
 * @date 2020/12/29
 */
@Data
public class NotificationInfoEntity<T> {
    @ApiModelProperty("消息内容")
    private T msg;

    public  NotificationInfoEntity(T msg) {
        this.msg = msg;
    }
}
