package cn.iocoder.springboot.lab39.skywalkingdemo.producer;

import cn.iocoder.springboot.lab39.skywalkingdemo.config.RabbitConstants;
import cn.iocoder.springboot.lab39.skywalkingdemo.message.DemoMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DemoProducer {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void syncSend() {
        // 创建 DemoMessage 消息
        DemoMessage message = new DemoMessage();
        long timeMillis = System.currentTimeMillis();
        message.setId(timeMillis);
        // 同步发送消息
        rabbitTemplate.convertAndSend(RabbitConstants.EXCHANGE, RabbitConstants.ROUTING_KEY, message);
        logger.info("rabbitmq.threadId【{}】.time【{}】", Thread.currentThread().getId(),timeMillis);
    }

}
