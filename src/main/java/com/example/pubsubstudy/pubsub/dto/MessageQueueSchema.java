package com.example.pubsubstudy.pubsub.dto;

import com.example.pubsubstudy.pubsub.domain.PushType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class MessageQueueSchema {

    private final PushType pushType;
    private final String deviceToken;
    private final String title;
    private final String body;
    private Map<String, String> data;
}
