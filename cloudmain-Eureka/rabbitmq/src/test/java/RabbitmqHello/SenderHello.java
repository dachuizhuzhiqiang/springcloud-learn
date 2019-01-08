package RabbitmqHello;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.TimeoutException;

public class SenderHello {
    private static final String EXCHANG_NAME = "exchange_demo";
    private static final String ROUTING_KEY = "routing_demo";
    private static final String QUEUE_NAME = "queue_demo";
    private static final String IP_ADDRESS = "localhost";
    private static final int PORT = 5672;

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory=new ConnectionFactory();
        factory.setHost(IP_ADDRESS);
        factory.setPort(PORT);
        factory.setUsername("root");
        factory.setPassword("root");
        Connection connection=factory.newConnection();//创建连接
        Channel channel = connection.createChannel();//创建信道
        //创建一个type="direct",持久化的，非自定删除的交换器
        channel.exchangeDeclare(EXCHANG_NAME,"direct",true,false,null);
        //创建一个持久化，非排他的，非自定删除的队列
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);
        //将交换器与队列通过路由键绑定
        channel.queueBind(QUEUE_NAME,EXCHANG_NAME,EXCHANG_NAME);
        //发送一条持久化的消息
        String message="Hello World";
        for (int i = 0; i < 10; i++) {
            message="Hello World "+i;
            channel.basicPublish(EXCHANG_NAME,EXCHANG_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes());

        }
        //关闭资源
        channel.close();;
        connection.close();
    }
}
