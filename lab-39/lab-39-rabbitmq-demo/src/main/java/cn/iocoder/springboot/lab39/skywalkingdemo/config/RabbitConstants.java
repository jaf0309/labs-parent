package cn.iocoder.springboot.lab39.skywalkingdemo.config;


public interface RabbitConstants {
    public static final String QUEUE = "QUEUE_DEMO";
    public static final String EXCHANGE = "EXCHANGE_DEMO";
    public static final String ROUTING_KEY = "ROUTING_KEY";

    public static final String DEAD_LETTER_EXCHANGE = "delay.queue.demo.deadletter.exchange";
    public static final String DEAD_LETTER_QUEUEA_ROUTING_KEY = "delay.queue.demo.deadletter.delay_10s.routingkey";
    public static final String DEAD_LETTER_QUEUEA_NAME = "delay.queue.demo.deadletter.queuea";

}
