package com.nequi.events.domain.model;

import java.time.LocalDate;

public record Event(String eventId, String name, LocalDate date, String location, int totalCapacity, int availableCapacity) {
}
