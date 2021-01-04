package top.supoman.kafka.impl;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Component;
import top.websocket.server.WebServerEndpoint;
import top.websocket.vo.MessageObject;

/**
 * @author bz
 * @date 2020/12/29
 */
@Component
@RequiredArgsConstructor
public class KafkaConsumerImpl<T> {

    private final WebServerEndpoint webServerEndpoint;

    public void handle(ConsumerRecord<String, String> record) {
        //MessageObject response = JsonUtils.json2obj(record.value(), MessageObject.class);
        System.out.println(record.value());
        MessageObject object = new MessageObject();
        object.setData(record.value());
        object.setUserId(0L);
        webServerEndpoint.sendMessage(object);
    }


}
