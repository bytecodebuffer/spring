package top.websocket.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.websocket.enums.NotificationTypeEnum;
import top.websocket.param.SendParam;
import top.websocket.server.WebServerEndpoint;
import top.websocket.vo.MessageObject;
import top.websocket.vo.NotificationInfoEntity;

/**
 * @author bz
 * @date 2020/9/17
 */
@Controller
@Api(tags = "WebSocketController",description = "webSocket相关接口",hidden = false)
public class WebSocketController {
    @Autowired
    private WebServerEndpoint webServerEndpoint;

    @ApiOperation(value = "单人发送",hidden = false)
    @ResponseBody
    @GetMapping("/sendOne")
    public String sendOne(SendParam param){
        MessageObject<String> messageObject = MessageObject.data(param.getMessage(),
                NotificationTypeEnum.PING, param.getUserId());
         webServerEndpoint.sendMessage(messageObject);
         return null;
    }

    @ApiOperation(value = "批量群发",hidden = false)
    @ResponseBody
    @GetMapping("/sendBatch")
    public void sendBatch(String message){
        MessageObject<NotificationInfoEntity<String>> messageObject = MessageObject.info(message);
        webServerEndpoint.sendMessage(messageObject);
    }
}
