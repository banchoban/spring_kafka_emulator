package com.example.test;

import com.example.test.MessageEntity;
import com.example.test.MessageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Service
@RestController
@RequestMapping("/api/delay")
public class DatabaseService {

    private final MessageRepository messageRepository;
    private long delay = 1000;

    public DatabaseService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GetMapping
    public String getDelay() {
        return String.format("Current delay: %d", this.delay);
    }

    @PostMapping
    public String setDelay(@RequestParam long ms) {
        long oldDelay = this.delay;
        this.delay = ms;

        return String.format("Delay changed from %d to %d", oldDelay, this.delay);
    }

    @Transactional
    public void saveMessage(String msgUuid, Boolean head, Long receivedAtUnix) throws InterruptedException {
        MessageEntity entity = new MessageEntity();
        entity.setMsgUuid(msgUuid);
        entity.setHead(head);
        entity.setTimeRq(receivedAtUnix);

        Thread.sleep(this.delay);

        this.messageRepository.save(entity);
    }
}