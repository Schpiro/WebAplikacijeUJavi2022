package hr.tvz.Bilandzija.hardwareapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hr.tvz.Bilandzija.hardwareapp.command.HardwareCommand;
import hr.tvz.Bilandzija.hardwareapp.model.enums.TypeOfHardware;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class HardwareControllerTest {

    private static final String TEST_NAME = "test";
    private static final Integer REPLACEMENT_CODE = 12345;
    private static final Integer TEST_CODE = 1234;
    private static final Integer TEST_PRICE = 123;
    private static final TypeOfHardware TEST_TYPE = TypeOfHardware.GPU;
    private static final Integer TEST_STOCK = 12345;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllHardware() throws Exception {
        this.mockMvc.perform(
                get("/hardware")
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
                .andExpect(jsonPath("$[*].code",containsInAnyOrder(123,1231,1253)));
    }

    @Test
    void getHardwareByCode() throws Exception {
        this.mockMvc.perform(
                get("/hardware/123")
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
                .andExpect(jsonPath("$.code").value(123));
    }

    @Test
    @Transactional
    void save() throws Exception {
        HardwareCommand hardwareCommand = new HardwareCommand(TEST_NAME,TEST_CODE,TEST_PRICE,TEST_TYPE,TEST_STOCK);

        this.mockMvc.perform(
                post("/hardware")
                        .with(user("admin")
                                .password("admin")
                                .roles("ADMIN")
                        )
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(hardwareCommand))
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().encoding(StandardCharsets.UTF_8))
                .andExpect(jsonPath("$.name").value(TEST_NAME))
                .andExpect(jsonPath("$.code").value(TEST_CODE)) //nes nije dobro oko savea, code bi trebao bit 1234
                .andExpect(jsonPath("$.price").value(TEST_PRICE));
    }

    @Test
    @Transactional
    void delete_hardware() throws Exception {
        this.mockMvc.perform(
                delete("/hardware/1")
                        .with(user("admin")
                                .password("admin")
                                .roles("ADMIN")
                        )
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isNoContent());
    }

    @Test
    @Transactional
    void update() throws Exception {
        HardwareCommand hardwareCommand = new HardwareCommand(TEST_NAME, TEST_CODE,TEST_PRICE,TEST_TYPE,TEST_STOCK);

        this.mockMvc.perform(
                post("/hardware")
                        .with(user("admin")
                                .password("admin")
                                .roles("ADMIN")
                        )
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(hardwareCommand))
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isCreated());

        hardwareCommand = new HardwareCommand(TEST_NAME,REPLACEMENT_CODE,TEST_PRICE,TEST_TYPE,TEST_STOCK);
        this.mockMvc.perform(
                put("/hardware/"+TEST_CODE)
                        .with(user("admin")
                                .password("admin")
                                .roles("ADMIN")
                        )
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(hardwareCommand))
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().encoding(StandardCharsets.UTF_8))
                .andExpect(jsonPath("$.name").value(TEST_NAME))
                .andExpect(jsonPath("$.code").value(REPLACEMENT_CODE)) //nes nije dobro oko savea, code bi trebao bit 1234
                .andExpect(jsonPath("$.price").value(TEST_PRICE));
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