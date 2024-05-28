package com.example.sse.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter.SseEventBuilder;

import java.io.IOException;
import java.io.Writer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("sse")
public class SSEController {
	@GetMapping
	public void sseServlet(final HttpServletResponse response) throws IOException, InterruptedException {
		response.setContentType("text/event-stream");
		response.setCharacterEncoding("UTF-8");

		Writer writer = response.getWriter();

		for (int i = 0; i < 20; i++) {
			writer.write("data: " + System.currentTimeMillis() + "\n\n");
			writer.flush(); // 꼭 flush 해주어야 한다
			Thread.sleep(1000);
		}

		writer.close();
	}

	@GetMapping("emitter")
	public SseEmitter sseEmitter() {
		SseEmitter emitter = new SseEmitter();
		ExecutorService sseMvcExecutor = Executors.newSingleThreadExecutor();

		sseMvcExecutor.execute(() -> {
			try {
				for (int i = 0; i < 20; i++) {
					SseEventBuilder event = SseEmitter.event()
							.data(System.currentTimeMillis());
					emitter.send(event);
					Thread.sleep(1000);
				}
			} catch (Exception e) {
				System.out.println("SSEController.sseEmitter()");
				e.printStackTrace();
			}
		});

		return emitter;
	}
}