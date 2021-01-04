package consumer.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author: qinrongjun
 * @date: 2020/10/13 10:48 上午
 */
@Component
@Slf4j
public class KafkaConsumerManager<T> {


    @KafkaListener(topics = {"${stl.config.kafka.topic.message-push}"})
    public void listenMessagePush(ConsumerRecord<String, String> record) {
        log.info(record.topic());
        log.info(record.value());
    }
}
