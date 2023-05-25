package com.clx.springboot30.application.controller;

import jakarta.annotation.Resource;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * http://localhost:3100/kafkaSend?msg=high
     * @param msg
     * @return
     */
    @RequestMapping("kafkaSend")
    public String kafkaSend(String msg) {
        kafkaTemplate.send("test1", msg);
        return "success";
    }

    /**
     * This is the consumer method that will be called when a message is sent to the topic "test1".
     * @param message
     */
    // 因为日志过于频繁刷新，所以暂时给注释掉了，方法是正常的
//    @KafkaListener(topics = {"test1"})
//    public void consumer(String message) {
//        System.out.println("test1 topic message :" + message);
//    }
}
