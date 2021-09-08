package com.example.pubsubstudy.pubsub.controller;

import com.example.pubsubstudy.PubsubStudyApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = {PubsubStudyApplication.class})
class PubSubIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void sendMessage() throws Exception {
        mockMvc.perform(
                post("/api/v1/sendMessage")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"title\":\"푸시 제목\",\"body\":\"중요한 내용\",\"deviceToken\":\"FakeDeviceToken\"}")
            )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success", is(true)))
            .andDo(print());
    }
}