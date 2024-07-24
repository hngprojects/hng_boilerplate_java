package hng_java_boilerplate.waitlist.controller;

import hng_java_boilerplate.email.EmailServices.EmailProducerService;
import hng_java_boilerplate.waitlist.controller.WaitlistController;
import hng_java_boilerplate.waitlist.entity.Waitlist;
import hng_java_boilerplate.waitlist.service.WaitlistService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WaitlistController.class)
public class WaitlistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WaitlistService waitlistService;

    @MockBean
    private EmailProducerService emailProducerService;

    @InjectMocks
    private WaitlistController waitlistController;

    @BeforeEach
    void setUp() {
        // No need for MockitoAnnotations.openMocks(this) as @MockBean handles mocking
    }

    @Test
    void createWaitlist() throws Exception {
        Waitlist waitlist = new Waitlist(UUID.randomUUID(), "Test User", "test@example.com");

        when(waitlistService.saveWaitlist(any(Waitlist.class))).thenReturn(waitlist);
        doNothing().when(emailProducerService).sendEmailMessage(any(String.class), any(String.class), any(String.class));

        mockMvc.perform(post("/api/v1/waitlist")
                        .contentType("application/json")
                        .content("{\"email\":\"test@example.com\",\"fullName\":\"Test User\"}"))
                .andExpect(status().isCreated());

        verify(waitlistService).saveWaitlist(any(Waitlist.class));
        verify(emailProducerService).sendEmailMessage("test@example.com", "Confirmation Email", "You are all signed up!");
    }

    @Test
    void createWaitlist_returnsExpectedResponse() {
        Waitlist waitlist = new Waitlist(UUID.randomUUID(), "Test User", "test@example.com");

        when(waitlistService.saveWaitlist(any(Waitlist.class))).thenReturn(waitlist);
        doNothing().when(emailProducerService).sendEmailMessage(any(String.class), any(String.class), any(String.class));

        ResponseEntity<?> response = waitlistController.createWaitlist(waitlist);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("You are all signed up!", ((Map<String, String>) response.getBody()).get("message"));
    }
}
