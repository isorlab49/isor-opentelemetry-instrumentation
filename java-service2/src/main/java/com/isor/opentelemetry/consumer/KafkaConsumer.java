package com.isor.opentelemetry.consumer;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.*;
import io.opentelemetry.context.Context;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Produced;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
@EnableKafka
@EnableKafkaStreams
public class KafkaConsumer {

    private Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @Autowired
    public void secondTopicPipeline(StreamsBuilder builder) {
        builder.stream("second-topic", Consumed.with(Serdes.String(), Serdes.String()))
                .peek((o, o2) -> logger.info("{} - {}", o, o2))
                .mapValues((s, s2) -> {
                    try {
                        Thread.sleep(ThreadLocalRandom.current().nextInt(50, 500));
                        return s2;
                    } catch (Exception e) {
                        return s2;
                    }
                })
                .to("third-topic", Produced.with(Serdes.String(), Serdes.String()));
    }
}
