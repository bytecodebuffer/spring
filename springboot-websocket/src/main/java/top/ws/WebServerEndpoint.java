package top.ws;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.model.Vo.MessageVO;
import top.util.ServerEncoder;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author bz
 * @date 2020/9/16
 */
@ServerEndpoint(value = "/socketServer/{userId}",encoders = {ServerEncoder.class})
@Component
@Slf4j
@Data
public class WebServerEndpoint {
    private static ConcurrentHashMap<String, Session> map = new ConcurrentHashMap<>();
    private static CopyOnWriteArraySet<WebServerEndpoint> sessionSet = new CopyOnWriteArraySet<>();
    private Session session;

    @OnOpen
    public void onOpen(@PathParam("userId")String userId, Session session){
        if(userId!=null){
            map.put(userId,session);
        }
        this.session = session;
        sessionSet.add(this);
        try {
            this.sendMessage("连接成功！", session);
        }catch (Exception e){
            System.out.println("ws IO异常");
        }
    }

    @OnClose
    public void onClose(Session session){
        sessionSet.remove(this);
        Collection<Session> values = map.values();
        values.remove(this);
        this.sendMessage("连接关闭：",session);
    }

    @OnMessage
    public void OnMessage(String message,Session session){
        sendBatch(message);
    }

    @OnError
    public void  onError(Throwable error,Session session){
        log.error("服务端错误");
        this.sendMessage("服务端错误："+error.getMessage(),session);
    }


    public void sendMessage(String msg,Session session){
        log.info("服务端发送消息："+ msg);
        try {
            session.getBasicRemote().sendText(msg);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 单独发送消息
     */
    public String sendOne(String userId,String message){
        if(map.containsKey(userId) && map.get(userId)!=null){
            sendMessage(message,map.get(userId));
            return "success";
        }else{
            log.info("用户不存在");
            return "用户不存在";
        }
    }

    /**
     * 群发自定义消息
     */
    public void sendBatch(String message){
        log.info("【sendBatch】："+message);
        sessionSet.stream().forEach(
                item->item.sendMessage(message,item.getSession())
        );
    }

    /**
     * 发送对象
     * @param messageVo
     * @param session
     */
    public void sendObject(MessageVO messageVo, Session session){
        try {
            session.getBasicRemote().sendObject(messageVo);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 发送对象信息
     * @param message
     */
    public void sendBatchObject(MessageVO message){
        sessionSet.stream().forEach(
                item->item.sendObject(message,item.getSession())
        );
    }
}
