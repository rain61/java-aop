package com.ms.aop.service.impl;


import com.ms.aop.service.KafkaCustomerImpl;
import com.ms.aop.service.KafkaProducerImpl;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class KafkaCustomer implements KafkaCustomerImpl {



    public void sendMassage(String map) {

    }
    @KafkaListener(topics = {"exceptiontopic"})
    public void receiveMessage(String map) {
        System.err.println("--------开始接收消息--------");
        System.err.println("        接收消息 ："+map);
        System.err.println("--------结束接收消息--------");
    }
}
