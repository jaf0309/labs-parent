package cn.iocoder.springboot.lab39.skywalkingdemo.config;

import cn.iocoder.springboot.lab39.skywalkingdemo.message.DemoMessage;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitConfig {

    // 声明死信Exchange
    @Bean(RabbitConstants.DEAD_LETTER_EXCHANGE)
    public DirectExchange deadLetterExchange(){
        return new DirectExchange(RabbitConstants.DEAD_LETTER_EXCHANGE);
    }

    // 创建 Direct Exchange
    @Bean(RabbitConstants.EXCHANGE)
    public DirectExchange demoExchange() {
        return new DirectExchange(RabbitConstants.EXCHANGE,
                true,  // durable: 是否持久化
                false);  // exclusive: 是否排它
    }

    // 创建 Queue
    @Bean
    public Queue demoQueue() {
        Map<String, Object> args = new HashMap<>(4);
        // x-dead-letter-exchange    这里声明当前队列绑定的死信交换机
        args.put("x-dead-letter-exchange", RabbitConstants.DEAD_LETTER_EXCHANGE);
        // x-dead-letter-routing-key  这里声明当前队列的死信路由key
        args.put("x-dead-letter-routing-key", RabbitConstants.DEAD_LETTER_QUEUEA_ROUTING_KEY);
        // x-message-ttl  声明队列的TTL
        args.put("x-message-ttl", 60000);
        return new Queue(RabbitConstants.QUEUE, // Queue 名字
                true, // durable: 是否持久化
                false, // exclusive: 是否排它
                false,// autoDelete: 是否自动删除
                args);
    }


    // 声明死信队列A 用于接收延时10s处理的消息
    @Bean(RabbitConstants.DEAD_LETTER_QUEUEA_NAME)
    public Queue deadLetterQueueA() {
        return new Queue(RabbitConstants.DEAD_LETTER_QUEUEA_NAME);
    }

    // 声明队列与交换机绑定关系
    @Bean
    public Binding demoBinding() {
        return BindingBuilder.bind(demoQueue()).to(demoExchange()).with(RabbitConstants.ROUTING_KEY);
    }


    // 声明死信队列A绑定关系
    @Bean
    public Binding deadLetterBindingA(@Qualifier(RabbitConstants.DEAD_LETTER_QUEUEA_NAME) Queue queue,
                                      @Qualifier(RabbitConstants.DEAD_LETTER_EXCHANGE) DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(RabbitConstants.DEAD_LETTER_QUEUEA_ROUTING_KEY);
    }
}
