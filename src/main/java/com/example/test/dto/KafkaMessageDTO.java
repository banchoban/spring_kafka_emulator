package com.example.test.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class KafkaMessageDTO {

    @JsonProperty("msg_uuid")
    private String msgUUID;

    @JsonProperty("head")
    private Boolean head;

    @JsonProperty("method")
    private String method;

    @JsonProperty("uri")
    private String uri;

    public KafkaMessageDTO() {
    }

    public String getMsgUUID() {
        return msgUUID;
    }

    public void setMsgUUID(String msgUUID) {
        this.msgUUID = msgUUID;
    }

    public Boolean getHead() {
        return head;
    }

    public void setHead(Boolean head) {
        this.head = head;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
