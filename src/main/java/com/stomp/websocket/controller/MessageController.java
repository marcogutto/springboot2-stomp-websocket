package com.stomp.websocket.controller;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import domain.Message;

@Controller
public class MessageController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);

    @MessageMapping("/send.message")
    @SendTo("/topic/messages")
    public Message send(Message message) {
        LOGGER.info(String.format("Received message [%s] on `/app/chat` message mapping!", message.toString()));
        LocalDateTime timestamp = LocalDateTime.now();
        return new Message(message.getFrom(), message.getMessage(), timestamp);
    }
}