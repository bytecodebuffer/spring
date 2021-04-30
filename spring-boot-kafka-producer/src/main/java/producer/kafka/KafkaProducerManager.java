package producer.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;


/**
 * @author: qinrongjun
 * @date: 2020/9/7 7:05 下午
 */
@Slf4j
@Component
public class KafkaProducerManager implements SuccessCallback<SendResult<String , String>>,
        FailureCallback {

    @Autowired
    private  KafkaTopicConfig kafkaTopicConfig;

    @Autowired
    private  KafkaTemplate<String ,String> kafkaTemplate;


    public void sendMessage(String topic, String message) {
        ListenableFuture<SendResult<String, String>> resultListenableFuture = kafkaTemplate.send(topic,message);
        resultListenableFuture.addCallback(this, this);
    }

    @Override
    public void onSuccess(SendResult<String, String> stringStringSendResult) {
        log.info("成功："+stringStringSendResult.toString());
        assert stringStringSendResult != null;
    }

    @Override
    public void onFailure(Throwable throwable) {
        log.info("失败："+throwable.getMessage());
    }
}
