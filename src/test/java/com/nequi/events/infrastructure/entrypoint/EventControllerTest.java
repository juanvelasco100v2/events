package com.nequi.events.infrastructure.entrypoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nequi.events.application.usecase.CreateEventUseCase;
import com.nequi.events.application.usecase.DeleteEventUseCase;
import com.nequi.events.application.usecase.GetAllEventsUseCase;
import com.nequi.events.application.usecase.GetEventUseCase;
import com.nequi.events.domain.model.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class EventControllerTest {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private CreateEventUseCase createEventUseCase;

    @Mock
    private GetEventUseCase getEventUseCase;

    @Mock
    private GetAllEventsUseCase getAllEventsUseCase;

    @Mock
    private DeleteEventUseCase deleteEventUseCase;

    @InjectMocks
    private EventController eventController;

    @BeforeEach
    void setUp() {
        // Configuración Standalone: No necesita contexto de Spring Boot completo
        mockMvc = MockMvcBuilders.standaloneSetup(eventController).build();
        
        // Necesario para soportar LocalDate en Jackson si no está configurado por Spring
        objectMapper.findAndRegisterModules();
    }

    @Test
    void createEvent_ShouldReturnCreated() throws Exception {
        Event event = new Event("1", "Concert", LocalDate.now(), "Stadium", 100, 100);
        when(createEventUseCase.execute(any(Event.class))).thenReturn(event);

        mockMvc.perform(post("/events")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(event)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.eventId").value("1"));
    }

    @Test
    void getEvent_ShouldReturnOk_WhenExists() throws Exception {
        Event event = new Event("1", "Concert", LocalDate.now(), "Stadium", 100, 100);
        when(getEventUseCase.execute("1")).thenReturn(Optional.of(event));

        mockMvc.perform(get("/events/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.eventId").value("1"));
    }

    @Test
    void getEvent_ShouldReturnNotFound_WhenNotExists() throws Exception {
        when(getEventUseCase.execute("1")).thenReturn(Optional.empty());

        mockMvc.perform(get("/events/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getAllEvents_ShouldReturnList() throws Exception {
        Event event = new Event("1", "Concert", LocalDate.now(), "Stadium", 100, 100);
        when(getAllEventsUseCase.execute()).thenReturn(List.of(event));

        mockMvc.perform(get("/events"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].eventId").value("1"));
    }

    @Test
    void deleteEvent_ShouldReturnNoContent() throws Exception {
        doNothing().when(deleteEventUseCase).execute("1");

        mockMvc.perform(delete("/events/1"))
                .andExpect(status().isNoContent());
    }
}
