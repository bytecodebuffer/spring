package com.bai;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.concurrent.TimeUnit;

public class Producer {
    public static void main(String[] args) throws Exception{

        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        //设置 RabbitMQ 地址
        factory.setHost("localhost");

        //建立到代理服务器连接
        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();

        String exchangeName = "test-exchange";
        channel.exchangeDeclare(exchangeName,"direct",true);

        String routingKey = "key";

        for(int i=0;i<100;i++){
            byte[] messageBodyBytes = ("msg"+i).getBytes();
            channel.basicPublish(exchangeName,routingKey,null,messageBodyBytes);
            TimeUnit.SECONDS.sleep(1);
        }


        channel.close();
        connection.close();

    }
}
