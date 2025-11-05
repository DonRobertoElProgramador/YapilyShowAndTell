package rae.yapily.authorization_service.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQQueueWriterService {

    private final RabbitTemplate template;

    @Value("${yapily.queue}")
    private String queueName;

    public RabbitMQQueueWriterService(RabbitTemplate template) {
        this.template = template;
    }

    public void send(String payload) {
        template.convertAndSend(queueName, payload);
    }
}