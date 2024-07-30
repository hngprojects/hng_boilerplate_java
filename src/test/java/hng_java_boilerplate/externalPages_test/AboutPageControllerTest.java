package hng_java_boilerplate.externalPages.controller;

import hng_java_boilerplate.externalPages.dtos.AboutPageDTO;
import hng_java_boilerplate.externalPages.services.AboutPageService;
import hng_java_boilerplate.user.enums.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AboutPageControllerTest {

    @Mock
    private AboutPageService aboutPageService;

    @InjectMocks
    private AboutPageController aboutPageController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAboutPage_Unauthenticated() {
        // Act
        ResponseEntity<?> response = aboutPageController.getAboutPage(null);

        // Assert
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    void testGetAboutPage_Unauthorized() {
        // Arrange
        Authentication mockAuth = mock(Authentication.class);
        when(mockAuth.getAuthorities()).thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<?> response = aboutPageController.getAboutPage(mockAuth);

        // Assert
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    void testUpdateAboutPage_Unauthenticated() {
        // Arrange
        AboutPageDTO aboutPageDTO = new AboutPageDTO();

        // Act
        ResponseEntity<?> response = aboutPageController.updateAboutPage(aboutPageDTO, null);

        // Assert
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    void testUpdateAboutPage_Unauthorized() {
        // Arrange
        Authentication mockAuth = mock(Authentication.class);
        when(mockAuth.getAuthorities()).thenReturn(Collections.emptyList());

        AboutPageDTO aboutPageDTO = new AboutPageDTO();

        // Act
        ResponseEntity<?> response = aboutPageController.updateAboutPage(aboutPageDTO, mockAuth);

        // Assert
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

}

