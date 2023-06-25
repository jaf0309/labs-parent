package cn.iocoder.springboot.lab39.skywalkingdemo.consumer;

import cn.iocoder.springboot.lab39.skywalkingdemo.config.RabbitConstants;
import cn.iocoder.springboot.lab39.skywalkingdemo.message.DemoMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DemoConsumer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    //@RabbitHandler
    @RabbitListener(queues = RabbitConstants.DEAD_LETTER_QUEUEA_NAME)
    public void onMessage(DemoMessage message) {
        logger.info("线程编号:{} 消息内容：{}.time【{}】", Thread.currentThread().getId(), message,System.currentTimeMillis()-message.getId());
    }

}
