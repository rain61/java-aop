package com.ms.aop.service.impl;


import com.ms.aop.service.KafkaProducerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author huahua
 */
@Service
public class KafkaProducer implements KafkaProducerImpl {


  /**
     * 配置kafka系统组件
     */
    @Autowired
    KafkaAdmin kafkaAdmin;

    /**
     * kafkaTemplate 发送消息Template
     */
    @Autowired
    KafkaTemplate kafkaTemplate;

    Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    //kafka 发送消息
    public void sendMassage(Map<String, Object> map) {
      //发送消息

      kafkaTemplate.send("exceptiontopic", map.toString());
    }
}
