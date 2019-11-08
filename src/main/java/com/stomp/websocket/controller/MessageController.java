package com.stomp.websocket.controller;

import java.time.LocalDateTime;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import domain.Message;

@RestController
@RequestMapping(value = "/v0/api")
public class MessageController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);
    
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/send.message")
    @SendToUser("/queue/token.private.messages")
    public Message send(Message message) {
        LOGGER.info(String.format("Received message [%s] on `/app/chat` message mapping!", message.toString()));
        LocalDateTime timestamp = LocalDateTime.now();
        return new Message(message.getFrom(), message.getMessage(), timestamp);
    }
    
    @PostMapping("/send")
    public boolean send(@Valid @RequestBody Map<String, Object> message) {
		try {
			LocalDateTime timestamp = LocalDateTime.now();
//			this.send(new Message("5521966744953", "Ola", timestamp));
			
			 this.simpMessagingTemplate.convertAndSend("/queue/token.private.messages", new Message("5521966744953", "Ola", timestamp));
			return true;
		} catch (Exception e) {
			return false;
		}
    }
}