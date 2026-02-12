package com.nequi.events.infrastructure.config;

import com.nequi.events.application.usecase.CreateEventUseCase;
import com.nequi.events.application.usecase.DeleteEventUseCase;
import com.nequi.events.application.usecase.GetAllEventsUseCase;
import com.nequi.events.application.usecase.GetEventUseCase;
import com.nequi.events.domain.repository.EventRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public CreateEventUseCase createEventUseCase(EventRepository eventRepository) {
        return new CreateEventUseCase(eventRepository);
    }

    @Bean
    public GetEventUseCase getEventUseCase(EventRepository eventRepository) {
        return new GetEventUseCase(eventRepository);
    }

    @Bean
    public GetAllEventsUseCase getAllEventsUseCase(EventRepository eventRepository) {
        return new GetAllEventsUseCase(eventRepository);
    }

    @Bean
    public DeleteEventUseCase deleteEventUseCase(EventRepository eventRepository) {
        return new DeleteEventUseCase(eventRepository);
    }
}
