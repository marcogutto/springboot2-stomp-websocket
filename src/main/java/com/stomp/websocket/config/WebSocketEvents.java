package com.stomp.websocket.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEvents {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketEvents.class);

//	@Autowired
//	private ChatRoomService chatRoomService;?
	
	@EventListener
	private void handleSessionConnected(SessionConnectEvent event) {
		SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(event.getMessage());
		String token = headers.getNativeHeader("token").get(0);
		headers.getSessionAttributes().put("token", token);
		LOGGER.info("Connect: "+token);
//		ChatRoomUser joiningUser = new ChatRoomUser(event.getUser().getName());
		
//		chatRoomService.join(joiningUser, chatRoomService.findById(chatRoomId));
	}

	@EventListener
	private void handleSessionDisconnect(SessionDisconnectEvent event) {
		SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(event.getMessage());
		String token = headers.getSessionAttributes().get("token").toString();
		LOGGER.info("Disconnect: "+token);
//		ChatRoomUser leavingUser = new ChatRoomUser(event.getUser().getName());

//		chatRoomService.leave(leavingUser, chatRoomService.findById(chatRoomId));
	}
}