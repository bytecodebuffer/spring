package top.supoman.kafka;

import top.supoman.kafka.impl.KafkaConsumerImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import top.websocket.vo.MessageObject;

/**
 * @author: qinrongjun
 * @date: 2020/10/13 10:48 上午
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumerManager<T> {

    private final KafkaConsumerImpl<MessageObject<T>> messagePushHandler;


    @KafkaListener(topics = {"message.push.test"})
    public void listenMessagePush(ConsumerRecord<String, String> record) {
        messagePushHandler.handle(record);
    }
}
