package com.example.pubsubstudy.pubsub.controller;

import com.example.pubsubstudy.PubsubStudyApplication;
import com.example.pubsubstudy.pubsub.dto.MessageContent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = {PubsubStudyApplication.class})
class PubSubControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    private String appUrl;

    @BeforeEach
    public void initializeAppUrl() {
        this.appUrl = "http://localhost:" + this.port;
    }

    @Test
    public void sendMessage() {
        String url = UriComponentsBuilder
            .fromHttpUrl(this.appUrl + "/api/v1/sendMessage")
            .toUriString();

        MessageContent content = new MessageContent("Good Title", "Important Body3");
        ResponseEntity<String> res = this.testRestTemplate.postForEntity(url, content, String.class);

        System.out.println(res.getBody());
        System.out.println(res.getStatusCodeValue());
    }
}