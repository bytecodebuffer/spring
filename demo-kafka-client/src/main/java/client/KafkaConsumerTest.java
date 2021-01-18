package client;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

/**
 * @author bz
 * @date 2020/9/5
 */
public class KafkaConsumerTest implements Runnable{

    private final KafkaConsumer<String,String> consumer;
    private final String topic;
    private ConsumerRecords<String,String> msgList;

    public KafkaConsumerTest(String topicName){
        Properties properties = new Properties();
        properties.put("bootstrap.servers","kafka-test-dev.forexmedia.com:9092,kafka-test-dev.forexmedia.com:9093,kafka-test-dev.forexmedia.com:9094,kafka-test-dev.forexmedia.com:9095");
        properties.put("enable.auto.commit","false");
        properties.put("auto.commit.interval. ", "1");
        properties.put("session.timeout.ms", "10000");
        properties.put("auto.offset.reset","latest");
        properties.put("group.id","myGroup");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        this.consumer = new KafkaConsumer<String, String>(properties);
        this.topic = topicName;
        this.consumer.subscribe(Arrays.asList(topic));
    }

    @Override
    public void run() {
        int messageNo = 1;
        System.out.println("---------开始消费---------");
        try {
           while (true){
                msgList = consumer.poll(Duration.ofSeconds(1));
                if(null!=msgList&&msgList.count()>0){
                    for (ConsumerRecord<String, String> record : msgList) {
                        System.out.println("第"+messageNo+"条消息----【key】：" + record.key() + ", 【value】：" + record.value()+"，【offset】："+record.offset());
                        messageNo++;
                        Thread.sleep(10000);
                    }
                }else{
                    Thread.sleep(10000);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            consumer.close();
        }
    }

    public static void main(String[] args) {
        KafkaConsumerTest kafka_test = new KafkaConsumerTest("message.push.test");
        Thread thread1 = new Thread(kafka_test);
        thread1.start();
    }
}
