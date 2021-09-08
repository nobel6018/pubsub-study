package com.example.pubsubstudy.pubsub.controller;

import com.example.pubsubstudy.pubsub.domain.PushType;
import com.example.pubsubstudy.pubsub.dto.MessageContent;
import com.example.pubsubstudy.pubsub.dto.MessageQueueSchema;
import com.example.pubsubstudy.pubsub.dto.SendMessageResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
@RestController
public class PubSubController {

    private final PubSubTemplate pubSubTemplate;
    private final ObjectMapper objectMapper;

    @Value("${spring.cloud.gcp.topic-name}")
    private String topicName;

    // IAM key 만들기
    // https://cloud.google.com/iam/docs/creating-managing-service-account-keys?hl=ko#iam-service-account-keys-create-gcloud
    @PostMapping("/api/v1/sendMessage")
    public ResponseEntity<SendMessageResponse> sendMessage(
        @RequestBody MessageContent content
    ) throws JsonProcessingException {
        MessageQueueSchema queueSchema = new MessageQueueSchema(
            PushType.SINGLE_MESSAGE,
            content.getDeviceToken(),
            content.getTitle(),
            content.getBody(),
            content.getData()
        );
        String message = objectMapper.writeValueAsString(queueSchema);
        log.info("message: " + message);

        this.pubSubTemplate.publish(topicName, message);

        return ResponseEntity.ok(new SendMessageResponse(true));
    }
}
