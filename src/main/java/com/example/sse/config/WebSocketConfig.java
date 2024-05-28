package com.example.sse.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@RequiredArgsConstructor
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
	private final WebSocketHandler webSocketHandler;

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(webSocketHandler, "ws/chat").setAllowedOrigins("*");
	}
}

//WebConfigurer는 WebSocket을 활성화 하고, WebSocketHandler를 등록할 수 있게 해준다.
//
//registerWebSocketHandlers 메서드를 통해 WebSocketHandler를 등록할 수 있다.
//
//또한 registerWebSocketHandlers에 연결할 WebSocket 엔드 포인트("ws/chat")을 등록할 수 있다.
//
//setAllowedOrigin은 지정한 Origin에서 오는 요청만 허용한다. *은 모든 요청을 허용한다.

//위 코드에선 WebSocketConfigurer를 구현하고 WebSocketHandler, 엔드포인트를 "ws/chat"으로 등록하고 모든 Origin에서 오는 요청을 허용하도록 설정하였다.