package com.example.pubsubstudy.pubsub.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MessageContent {

    private final String title;
    private final String body;
}
