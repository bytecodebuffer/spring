package client;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

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
        properties.put("bootstrap.servers","81.68.206.246:9092,81.68.206.246:9093,81.68.206.246:9094");
        properties.put("enable.auto.commit","true");
        properties.put("auto.commit.interval. ", "1");
        properties.put("session.timeout.ms", "10000");
        properties.put("auto.offset.reset","earliest");
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
                msgList = consumer.poll(1000);
                if(null!=msgList&&msgList.count()>0){
                    for (ConsumerRecord<String, String> record : msgList) {
                        System.out.println("第"+messageNo+"条消息----【key】：" + record.key() + ", 【value】：" + record.value()+"，【offset】："+record.offset());
                        messageNo++;
                    }
                }else{
                    Thread.sleep(1000);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            consumer.close();
        }
    }

    public static void main(String[] args) {
        KafkaConsumerTest kafka_test = new KafkaConsumerTest("factory");
        Thread thread1 = new Thread(kafka_test);
        thread1.start();
    }
}
