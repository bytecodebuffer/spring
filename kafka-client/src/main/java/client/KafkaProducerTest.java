package client;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @author bz
 * @date 2020/9/5
 */
public class KafkaProducerTest implements Runnable{

    private final KafkaProducer<String,String> producer;
    private final String topic;

    public KafkaProducerTest(String topicName){
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "81.68.206.246:9092,81.68.206.246:9093,81.68.206.246:9094");
        properties.put("acks", "all");
        properties.put("retries", 0);
        properties.put("batch.size", 16384);
        properties.put("key.serializer", StringSerializer.class.getName());
        properties.put("value.serializer", StringSerializer.class.getName());
        this.producer = new KafkaProducer<String, String>(properties);
        this.topic = topicName;
    }


    @Override
    public void run() {
        int msgNumber = 1;
        try {
            while (true){
                String messageStr="你好，这是第"+msgNumber+"条数据";
                Thread.sleep(500);
                producer.send(new ProducerRecord<String, String>(topic, "msg"+msgNumber, messageStr));
                System.out.println("发送的信息:" + messageStr);
                if(msgNumber%20==0){
                    System.out.println("成功发送了"+msgNumber+"条");
                    break;
                }
                msgNumber++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }
    }

    public static void main(String[] args) {
        KafkaProducerTest test = new KafkaProducerTest("factory");
        Thread thread = new Thread(test);
        thread.start();
    }
}
