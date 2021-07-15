package com.bai;

import com.rabbitmq.client.*;
import java.io.IOException;

public class Consumer {
    public static void main(String[] args) throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setHost("localhost");

        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();

        String exchangeName = "test-exchange";
        channel.exchangeDeclare(exchangeName,"direct",true);

        String queueName = channel.queueDeclare().getQueue();
        String routingKey = "key";

        channel.queueBind(queueName,exchangeName,routingKey);

        while (true){
            boolean autoAck = false;
            String consumerTag = "";

            channel.basicConsume(queueName,autoAck,consumerTag,new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String routingKey = envelope.getRoutingKey();
                    String contentType = properties.getContentType();

                    long deliveryTag = envelope.getDeliveryTag();
                    //确认消息
                    channel.basicAck(deliveryTag,false);
                    System.out.print("get msg:");
                    String bodyStr = new String(body,"UtF-8");
                    System.out.println(bodyStr);
                }
            });
        }

    }
}
