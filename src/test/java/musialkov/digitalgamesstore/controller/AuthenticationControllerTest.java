package musialkov.digitalgamesstore.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import musialkov.digitalgamesstore.dto.AuthenticationRequest;
import musialkov.digitalgamesstore.dto.AuthenticationResponse;
import musialkov.digitalgamesstore.dto.RegisterRequest;
import musialkov.digitalgamesstore.service.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationControllerTest {

    @Mock
    private AuthenticationService authenticationService;
    @InjectMocks
    private AuthenticationController authenticationController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(authenticationController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void register() throws Exception {
        RegisterRequest request = new RegisterRequest("testuser", "testemail", "testpassword");
        AuthenticationResponse response = new AuthenticationResponse("testtoken");

        Mockito.when(authenticationService.register(any(RegisterRequest.class))).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.token").value("testtoken"));
    }

    @Test
    void authenticate() throws Exception {
        AuthenticationRequest request = new AuthenticationRequest("testuser", "testpassword");
        AuthenticationResponse response = new AuthenticationResponse("testtoken");

        Mockito.when(authenticationService.authenticate(any(AuthenticationRequest.class))).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/authenticate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.token").value("testtoken"));
    }
}