package com.rgt_ws.ws_server.order;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.rgt_ws.ws_server.common.dto.MessageDto;
import com.rgt_ws.ws_server.order.domain.Order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class OrderWebsocketController {
    private final SimpMessagingTemplate messagingTemplate;

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void receiveMessage(Order message) {
        try {
            log.info("Received message from queue: {}", message);
            // MessageDto로 감싸서 전송
            MessageDto<Order> messageDto = new MessageDto<>("CREATE", message);
            messagingTemplate.convertAndSend("/topic/orders", messageDto);
        } catch (Exception e) {
            log.error("Error processing message: {}", e.getMessage());
        }
    }
}
