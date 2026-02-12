package com.nequi.events.infrastructure.adapter.entity;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
public class EventEntity {
    private String eventId;
    private String name;
    private String date;
    private String location;

    public EventEntity() {
    }

    public EventEntity(String eventId, String name, String date, String location) {
        this.eventId = eventId;
        this.name = name;
        this.date = date;
        this.location = location;
    }

    @DynamoDbPartitionKey
    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
