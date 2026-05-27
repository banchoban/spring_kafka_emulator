package com.example.test;

import jakarta.persistence.*;

@Entity
@Table(name = "test_table")
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "msguuid")
    private String msgUuid;

    @Column(name = "head")
    private Boolean head;

    @Column(name = "timerq")
    private Long timeRq;


    public void setMsgUuid(String msgUuid) {
        this.msgUuid = msgUuid;
    }

    public void setHead(Boolean head) {
        this.head = head;
    }

    public void setTimeRq(Long timeRq) {
        this.timeRq = timeRq;
    }

}
