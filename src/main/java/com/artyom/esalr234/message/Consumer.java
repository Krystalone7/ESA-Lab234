package com.artyom.esalr234.message;

import com.artyom.esalr234.model.ChangeLog;
import com.artyom.esalr234.repositories.ChangeLogRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Consumer {

    private final ChangeLogRepository changeLogRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(Consumer.class);

    @RabbitListener(queues = {"${rabbitmq.queue}"})
    public void consume(ChangeLog changeLog) {
        LOGGER.info(String.format("GET Change in DB %s", changeLog.toString()));
        changeLogRepository.save(changeLog);
    }
}