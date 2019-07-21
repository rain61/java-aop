package com.ms.aop.service;

import java.util.Map;

public interface KafkaProducerImpl {

    void sendMassage(Map<String,Object> map);
}
