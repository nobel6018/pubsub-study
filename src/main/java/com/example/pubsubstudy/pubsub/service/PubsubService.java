package com.example.pubsubstudy.pubsub.service;

import com.example.pubsubstudy.pubsub.domain.PushType;
import com.example.pubsubstudy.pubsub.dto.PushSingleMessageResponse;
import com.example.pubsubstudy.pubsub.schema.SingleMessage;
import com.google.api.core.ApiFuture;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.protobuf.ByteString;
import com.google.protobuf.util.JsonFormat;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.TopicName;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

// https://cloud.google.com/pubsub/docs/quickstart-client-libraries
// https://cloud.google.com/pubsub/docs/publisher?hl=ko#using_schema
// https://cloud.google.com/docs/authentication/production#passing_variable
/*
 * protobuf로 스키마를 지정한 메시지를 pub/sub에 보내는 코드입니다
 * spring boot와 관련이 없고 java sdk로 돌아갑니다
 * It requires service account
 * export GOOGLE_APPLICATION_CREDENTIALS="/absolutePath/src/main/resources/pubsub-sa.json"
 * */

@Service
@Slf4j
public class PubsubService {

    @Value("${spring.cloud.gcp.project-id}")
    private String projectId;

    @Value("${spring.cloud.gcp.topic-id}")
    private String topicId;

    public PushSingleMessageResponse publishSingleMessage(final String token, final String title, final String body)
        throws IOException, ExecutionException, InterruptedException {

        TopicName topicName = TopicName.of(projectId, topicId);

        Publisher publisher = Publisher.newBuilder(topicName).build();

        SingleMessage singleMessage = SingleMessage.newBuilder()
            .setPushType(PushType.SINGLE_MESSAGE.toString())
            .setToken(token)
            .setTitle(title)
            .setBody(body)
            .build();

        PubsubMessage.Builder message = PubsubMessage.newBuilder();

        String jsonString = JsonFormat.printer().omittingInsignificantWhitespace().print(singleMessage);
        message.setData(ByteString.copyFromUtf8(jsonString));
        log.info("Publishing a JSON-formatted message:\n" + message);

        // Publish the message.
        ApiFuture<String> future = publisher.publish(message.build());
        log.info("Published message ID: " + future.get());

        publisher.shutdown();
        publisher.awaitTermination(1, TimeUnit.MINUTES);

        return new PushSingleMessageResponse(true);
    }
}
