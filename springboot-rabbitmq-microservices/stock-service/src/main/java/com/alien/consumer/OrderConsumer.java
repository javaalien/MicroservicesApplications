package com.alien.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.alien.dto.OrderEvent;

@Service
public class OrderConsumer {

	private Logger logger = LoggerFactory.getLogger(OrderConsumer.class);

	@RabbitListener(queues = "${rabbitmq.queue.order.name}")
	public void consume(OrderEvent event) {
		logger.info(String.format("Order event received => %s", event.toString()));
		// save order event data in database
	}

}
