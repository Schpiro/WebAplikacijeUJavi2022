package hr.tvz.Bilandzija.hardwareapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final ObjectMapper objectMapper = new ObjectMapper();


    @Test
    void getAllReviews() throws Exception {
        this.mockMvc.perform(
                get("/review")
                        .with(user("admin")
                                .password("admin")
                                .roles("ADMIN")
                        )
                        .with(csrf())
                        .header(HttpHeaders.AUTHORIZATION,"Bearer "+ createJwt())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().encoding(StandardCharsets.UTF_8))
                .andExpect(jsonPath("$[*].title").exists())
                .andExpect(jsonPath("$[*].text").exists())
                .andExpect(jsonPath("$[*].rating").exists());
    }

    @Test
    void getAllReviewByHardwareCode() throws Exception {
        this.mockMvc.perform(
                get("/review/?hardwareCode=123")
                        .with(user("admin")
                                .password("admin")
                                .roles("ADMIN")
                        )
                        .with(csrf())
                        .header(HttpHeaders.AUTHORIZATION,"Bearer "+ createJwt())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().encoding(StandardCharsets.UTF_8))
                .andExpect(jsonPath("$[*].title").exists())
                .andExpect(jsonPath("$[*].text").exists())
                .andExpect(jsonPath("$[*].rating").exists());
    }

    private String createJwt() throws Exception {
        Map<String,Object> body = new HashMap<>();
        body.put("username", "admin");
        body.put("password", "admin");

        MvcResult mvcResult = this.mockMvc.perform(
                post("/authentication/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body))

        ).andExpect(status().isOk()).andReturn();

        String token = mvcResult.getResponse().getContentAsString();

        token = token.replace("{\"jwt\":\"", "")
                .replace("\"}", "");

        return token;
    }
}