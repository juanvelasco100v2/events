package com.nequi.events.infrastructure.adapter.entity;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.time.LocalDate;

@DynamoDbBean
public class EventEntity {
    private String eventId;
    private String name;
    private LocalDate date;
    private String location;
    private int totalCapacity;
    private int availableCapacity;

    public EventEntity() {
    }

    public EventEntity(String eventId, String name, LocalDate date, String location, int totalCapacity, int availableCapacity) {
        this.eventId = eventId;
        this.name = name;
        this.date = date;
        this.location = location;
        this.totalCapacity = totalCapacity;
        this.availableCapacity = availableCapacity;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(int totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public int getAvailableCapacity() {
        return availableCapacity;
    }

    public void setAvailableCapacity(int availableCapacity) {
        this.availableCapacity = availableCapacity;
    }
}
