package top.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;

/**
 * @author bz
 * @date 2020/12/29
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaProducerManager implements SuccessCallback<SendResult<String , String>>, FailureCallback {

    private final KafkaTemplate<String ,String> kafkaTemplate;


    public void sendMessage(String topic, String message) {
        ListenableFuture<SendResult<String, String>> resultListenableFuture = kafkaTemplate.send(topic,message);
        resultListenableFuture.addCallback(this, this);
    }

    @Override
    public void onSuccess(SendResult<String, String> stringStringSendResult) {
        assert stringStringSendResult != null;
        log.info(stringStringSendResult.getRecordMetadata().toString());
    }

    @Override
    public void onFailure(Throwable throwable) {
        log.error(throwable.getLocalizedMessage());
    }

}
