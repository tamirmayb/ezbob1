package com.ezbob.log.service;

import lombok.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.List;

// Service class that will consume the message from the given queue as soon as they are published/pushed to the queue.
@Service
public class LoggerService {

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    private static class MyDto {
        private int input;
        private List<Integer> result;
    }

    @RabbitListener(queues = "${app.queue}")
    public void getMessages(final MyDto dto) {
        System.out.println("Received following message from rabbitmq= " + dto);

    }
}