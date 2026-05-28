package com.example.test;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.example.test.dto.KafkaMessageDTO;

@Component
public class KafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);
    private final DatabaseService databaseService;
    private final ObjectMapper objectMapper;

    public KafkaConsumer(DatabaseService databaseService) {
        this.databaseService = databaseService;
        this.objectMapper = new ObjectMapper();
    }

    @KafkaListener(
            topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}",
            concurrency = "${kafka.listener.concurrency:1}"
    )
    public void consume(ConsumerRecord<String, String> record, Acknowledgment ack) {
        log.info(record.value());
        long unixTime = System.currentTimeMillis();

        try {
            KafkaMessageDTO message = objectMapper.readValue(record.value(), KafkaMessageDTO.class);
            this.databaseService.saveMessage(message.getMsgUUID(), message.getHead(), unixTime);
            ack.acknowledge();
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }
}