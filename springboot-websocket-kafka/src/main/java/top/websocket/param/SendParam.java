package top.websocket.param;

import lombok.Data;

/**
 * @author bz
 * @date 2020/9/17
 */
@Data
public class SendParam {
    private Long userId;
    private String message;
}
