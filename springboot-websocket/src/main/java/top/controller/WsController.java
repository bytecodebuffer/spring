package top.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.model.Vo.MessageVO;
import top.model.param.SendOneParam;
import top.ws.WebServerEndpoint;

import java.util.UUID;


/**
 * @author bz
 * @date 2020/9/16
 */
@Controller
@Api(tags = "WsController",description = "webSocket相关接口")
public class WsController {
    @Autowired
    private WebServerEndpoint webServerEndpoint;

    @ApiOperation(value = "首页跳转",hidden = true)

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @ApiOperation(value = "首页跳转",hidden = true)
    @GetMapping("/")
    public String index2(){
        return "redirect:/index";
    }

    @ApiOperation(value = "单人发送",hidden = false)
    @ResponseBody
    @GetMapping("/sendOne")
    public String sendOne(SendOneParam param){
        return webServerEndpoint.sendOne(param.getUserId(),param.getMessage());
    }

    @ApiOperation(value = "批量群发",hidden = false)
    @ResponseBody
    @GetMapping("/sendBatch")
    public void sendBatch(String message){
        webServerEndpoint.sendBatch(message);
    }

    @ApiOperation(value = "服务端广播5条")
    @ResponseBody
    @GetMapping("/sendBatch5")
    public void sendBatch5(){
        for(int i=1;i<6;i++) {
            webServerEndpoint.sendBatch("服务端广播消息：第"+i+"条！");
        }
    }

    @ApiOperation(value = "批量群发Object",hidden = false)
    @ResponseBody
    @GetMapping("/sendBatchObject")
    public void sendBatchObject(String message){
        MessageVO messageVO = new MessageVO();
        messageVO.setSeq(UUID.randomUUID().toString());
        messageVO.setUserId("userId");
        messageVO.setData(message);
        webServerEndpoint.sendBatchObject(messageVO);
    }
}
