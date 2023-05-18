package com.isor.opentelemetry.consumer;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.*;
import io.opentelemetry.context.Context;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    private Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @Autowired
    private KafkaTemplate<String, String> template;

    @KafkaListener(topics = {"first-topic"})
    public void firstReceiver(ConsumerRecord<String, String> stringRecord) {
        logger.info("{} - {} received", stringRecord.key(), stringRecord.value());
        ProducerRecord<String, String> providedRecord = new ProducerRecord<>("second-topic", stringRecord.key(), stringRecord.value());
        template.send(providedRecord);
    }
}
