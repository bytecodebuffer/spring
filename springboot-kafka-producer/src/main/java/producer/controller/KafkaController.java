package producer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import producer.kafka.KafkaProducerManager;

/**
 * @author bz
 * @date 2021/1/4
 */
@Slf4j
@RestController
public class KafkaController {

    @Autowired
    private KafkaProducerManager kafkaProducerManager;

    @Value("${stl.config.kafka.topic.message-push}")
    private String topic;

    @RequestMapping("/send")
    public void send(String msg){
        log.info("发送信息:"+msg);
        kafkaProducerManager.sendMessage(topic,msg);
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }
}
