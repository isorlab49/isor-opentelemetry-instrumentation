package com.isor.opentelemetry.controller;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.SpanKind;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Context;
import io.opentelemetry.context.Scope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/api")
public class Rollcontroller {

    private static final Logger logger = LoggerFactory.getLogger(Rollcontroller.class);

    @Autowired
    private KafkaTemplate<String, String> template;

    @GetMapping("/roll")
    public String index(@RequestParam("player") Optional<String> player) {
        int result = this.getRandomNumber(1,6);
            if(player.isPresent()) {
                template.send("first-topic", player.get());
                logger.info("{} is rolling the dice: {}", player.get(), result);
            } else {
                template.send("first-topic", "Anonymus");
                logger.info("Anonymus roll: {}", result);
            }
        return Integer.toString(result);
    }


    public int getRandomNumber(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max+1);
    }
}
