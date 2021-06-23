package com.skillhunt;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.skillhunt.controller.AuthController;
import com.skillhunt.db.InitialLoader;
import com.skillhunt.initialize.Postgres;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.annotation.PostConstruct;
import java.io.IOException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SkillHuntApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles({"base-test", "local-test", "ValidationMessages.properties"})
@ContextConfiguration(initializers = {
        Postgres.Initializer.class
})
public abstract class ResolversAbstractCommonConfiguration {

    protected final static String ADMIN = "admin";
    protected final static String PASSWORD = "1234";

    @BeforeAll
    static void init() {
        Postgres.container.start();
    }

    @Autowired
    protected InitialLoader initialLoader;

    @Autowired
    protected MockMvc mockMvc;

    protected static ObjectMapper objectMapper;

    static
    {
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Autowired
    protected AuthController authController;

    @PostConstruct
    private void initialData() throws IOException {
        this.initialLoader.initial();

    }

    protected ResultActions sendToController(Object obj, String url, boolean isStatusOk) throws Exception {
        return this.mockMvc
                .perform(
                        post(url)
                                .content(objectMapper.writeValueAsString(obj))
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print()).andExpect(!isStatusOk ? status().isBadRequest() : status().isOk());
    }
}