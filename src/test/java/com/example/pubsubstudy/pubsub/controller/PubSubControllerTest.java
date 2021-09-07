package com.example.pubsubstudy.pubsub.controller;

import com.example.pubsubstudy.PubsubStudyApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = {PubsubStudyApplication.class})
@AutoConfigureMockMvc
class PubSubControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void sendMessageTest() throws Exception {
        mockMvc.perform(
                post("/api/v1/sendMessage")
                    .content("{\"title\":\"Good Title!!\",\"body\":\"Important Message!!\"}")
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk())
            .andDo(print());
    }
}