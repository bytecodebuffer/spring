package top.supoman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.supoman.kafka.KafkaProducerManager;

/**
 * @author bz
 * @date 2020/12/29
 */
@RestController
@RequestMapping("/kafka")
public class KafkaConsumerController {

    @Autowired
    private KafkaProducerManager kafkaProducerManager;

    @GetMapping("/send")
    public void kafkaSend(String msg){
        kafkaProducerManager.sendMessage("message.push.test",msg);
    }

}
