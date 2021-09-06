package com.example.pubsubstudy.pubsub.controller;

import com.example.pubsubstudy.pubsub.dto.MessageContent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.pubsub.v1.Subscriber;
import com.google.cloud.spring.pubsub.PubSubAdmin;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PubSubController {

    private final PubSubTemplate pubSubTemplate;
    private final PubSubAdmin pubSubAdmin;
    private final ArrayList<Subscriber> allSubscribers;
    private final ObjectMapper objectMapper;

    @Value("${spring.cloud.gcp.topic-name}")
    private String topicName;

    // IAM key 만들기
    // https://cloud.google.com/iam/docs/creating-managing-service-account-keys?hl=ko#iam-service-account-keys-create-gcloud
    @PostMapping("/api/v1/sendMessage")
    public ResponseEntity<Object> sendMessage(
        @RequestBody MessageContent content
    ) throws JsonProcessingException {
        log.info("topicName: " + topicName);

        String jsonString = objectMapper.writeValueAsString(content);
        log.info("message: " + jsonString);
        this.pubSubTemplate.publish(topicName, jsonString);

        return ResponseEntity.ok("done");
    }

}
