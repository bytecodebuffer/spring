package websocket.server;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import util.JsonUtils;
import websocket.vo.MessageObject;
import websocket.vo.NotificationInfoEntity;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author bz
 * @date 2020/9/17
 */
@ServerEndpoint(value = "/socketServer/{userId}", encoders = {ServerEncoder.class})
@Component
@Slf4j
@Data
public class WebServerEndpoint {
    private static ConcurrentHashMap<Long, Session> map = new ConcurrentHashMap<>();
    private static CopyOnWriteArraySet<WebServerEndpoint> sessionSet = new CopyOnWriteArraySet<>();
    private Session session;

    @OnOpen
    public void onOpen(@PathParam("userId") Long userId, Session session) {
        if (userId != null) {
            map.put(userId, session);
        }
        this.session = session;
        sessionSet.add(this);
        try {
            MessageObject<NotificationInfoEntity<String>> messageObject = MessageObject.info("连接成功");
            this.sendMessage(messageObject, session);
        } catch (Exception e) {
            System.out.println("ws IO异常");
        }
    }

    @OnClose
    public void onClose(Session session) {
        sessionSet.remove(this);
        Collection<Session> values = map.values();
        values.remove(this);
        MessageObject<NotificationInfoEntity<String>> messageObject = MessageObject.info("连接关闭");
        this.sendMessage(messageObject, session);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        MessageObject<NotificationInfoEntity<String>> messageObject = MessageObject.info(message);
        sendMessage(messageObject, session);
    }

    @OnError
    public void onError(Throwable error, Session session) {
        log.info(session.getId());
        MessageObject<NotificationInfoEntity<String>> messageObject = MessageObject.info("服务端错误");
        this.sendMessage(messageObject, session);
    }


    public <T> void sendMessage(MessageObject<T> msgObject, Session session) {
        if (!session.isOpen()) {
            return;
        }
        try {
            session.getBasicRemote().sendObject(msgObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 单独发送消息
     */
    public <T> boolean sendMessage(MessageObject<T> msgObject) {
        if (null == msgObject) {
            return false;
        }
        Long userId = msgObject.getUserId();
        if (null == userId) {
            return false;
        }
        if (userId.equals(0L)) {
            sendBatchObject(msgObject);
            return true;
        }
        if (map.containsKey(userId) && map.get(userId) != null) {
            log.info("发送了----- {}" + JsonUtils.obj2json(msgObject));
            sendMessage(msgObject, map.get(userId));
            return true;
        } else {
            log.info("用户不存在");
            return false;
        }
    }

    /**
     * 群发自定义消息
     */
    private <T> void sendBatchObject(MessageObject<T> msgObject) {
        sessionSet.forEach(
                item -> item.sendMessage(msgObject, item.getSession())
        );
    }
}
