package com.example.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Service
@RestController
@RequestMapping("/api/delay")
public class DatabaseService {

    private static final Logger log = LoggerFactory.getLogger(DatabaseService.class);
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

        log.info(entity.toString());
        this.messageRepository.save(entity);
    }
}