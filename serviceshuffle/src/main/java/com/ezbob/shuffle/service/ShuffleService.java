package com.ezbob.shuffle.service;

import com.ezbob.shuffle.controller.MyController;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ShuffleService {

    private static Logger logger = LogManager.getLogger(MyController.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${app.queue}")
    private String queue;

    @Setter
    @Getter
    @AllArgsConstructor
    @ToString
    private static class MyDto {
        private int n;
        private List<Integer> result;
    }

    public List<Integer> createList(int n) {
        IntStream range = IntStream.rangeClosed(1, n);
        List<Integer> list = range.boxed().collect(Collectors.toList());
        Collections.shuffle(list);

        this.publishEventToRabbitMq(list, n);
        return list;
    }

    @Async
    private void publishEventToRabbitMq(final List<Integer> result, final int n) {
        final MyDto dto = new MyDto(n, result);
        logger.info("Sending the following event object to the queue: " + result);
        rabbitTemplate.convertAndSend(queue, dto);
        logger.info("Message successfully sent to the rabbitMq.");
    }
}
