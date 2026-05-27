package com.example.test;

import com.example.test.MessageEntity;
import com.example.test.MessageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@Service
public class DatabaseService {

    private final MessageRepository messageRepository;

    public DatabaseService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Transactional
    public void saveMessage(String msgUuid, Boolean head, Long receivedAtUnix) {
        MessageEntity entity = new MessageEntity();
        entity.setMsgUuid(msgUuid);
        entity.setHead(head);
        entity.setTimeRq(receivedAtUnix);

        this.messageRepository.save(entity);
    }
}