package com.example.moonshot.auth;

import com.example.moonshot.auth.dto.LoginRequest;
import com.example.moonshot.user.User;
import com.example.moonshot.user.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import java.util.Map;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AuthServiceTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    @MockBean
    private FirebaseTokenVerifier firebaseTokenVerifier;


    @BeforeEach
    void setUp() {
        userRepository.deleteAll();

        // Save one valid user to test login via email and phone
        userRepository.save(User.builder()
                .email("valid@example.com")
                .phone("1234567890")
                .name("Valid User")
                .passwordHash(passwordEncoder.encode("correctpassword"))
                .platform("EMAIL")
                .build());
    }

    @Test
    void shouldLoginSuccessfullyWithEmail() throws Exception {
        // Email-based login should succeed and return token + user info
        LoginRequest request = new LoginRequest();
        request.setIdentifier("valid@example.com");
        request.setPassword("correctpassword");

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists())
                .andExpect(jsonPath("$.userId").exists())
                .andExpect(jsonPath("$.email").value("valid@example.com"));
    }

    @Test
    void shouldLoginSuccessfullyWithPhone() throws Exception {
         // Phone-based login using same user should also work
        LoginRequest request = new LoginRequest();
        request.setIdentifier("1234567890");
        request.setPassword("correctpassword");

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists())
                .andExpect(jsonPath("$.email").value("valid@example.com"));
    }

    @Test
    void shouldFailWithWrongPassword() throws Exception {
        // Login should fail with incorrect password
        LoginRequest request = new LoginRequest();
        request.setIdentifier("valid@example.com");
        request.setPassword("wrongpassword");

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldFailWithUnknownEmail() throws Exception {
         // Login should fail if user does not exist
        LoginRequest request = new LoginRequest();
        request.setIdentifier("unknown@example.com");
        request.setPassword("any");

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldCreateUserViaGoogleSignIn() throws Exception {
         // Simulate Google sign-in with mocked token verification
        String idToken = "fake-google-token";

        when(firebaseTokenVerifier.verify(idToken)).thenReturn(Map.of(
                "email", "google@example.com",
                "name", "Google User"
        ));

        mockMvc.perform(post("/api/auth/google")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "idToken": "fake-google-token"
                        }
                    """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("google@example.com"))
                .andExpect(jsonPath("$.token").exists());

        // Verify user was saved with correct provider info
        User savedUser = userRepository.findByEmail("google@example.com").orElseThrow();
        assertEquals("GOOGLE", savedUser.getPlatform());
        assertEquals("fake-google-token", savedUser.getOauthToken());
    }

    @Test
    void shouldCreateUserViaFacebookSignIn() throws Exception {
        // Simulate Facebook sign-in with mocked token verification
        String idToken = "fake-fb-token";

        when(firebaseTokenVerifier.verify(idToken)).thenReturn(Map.of(
                "email", "facebook@example.com",
                "name", "Facebook User"
        ));

        mockMvc.perform(post("/api/auth/facebook")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "idToken": "fake-fb-token"
                        }
                    """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("facebook@example.com"))
                .andExpect(jsonPath("$.token").exists());

        // Verify user was saved with correct provider info
        User savedUser = userRepository.findByEmail("facebook@example.com").orElseThrow();
        assertEquals("FACEBOOK", savedUser.getPlatform());
        assertEquals("fake-fb-token", savedUser.getOauthToken());
    }


}
