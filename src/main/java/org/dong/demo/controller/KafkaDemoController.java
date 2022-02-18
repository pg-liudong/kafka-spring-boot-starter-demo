package org.dong.demo.controller;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * kafka演示
 *
 * @author liudong
 * @date 2022/2/18 10:14
 */
@RestController
@RequestMapping("/hello")
public class KafkaDemoController {

    @Resource(name = "ds1KafkaTemplate")
    private KafkaTemplate kafkaTemplate;

    @GetMapping("/")
    public void send() {
        kafkaTemplate.send("test", "hello world!");
    }

    /**
     * 处理发送消息
     *
     * @param message 参数
     */
    @KafkaListener(id = "test", topics = "test", containerFactory = "ds1KafkaListenerContainerFactory")
    public void receive(String message) {
        System.out.println(message);
    }

}
