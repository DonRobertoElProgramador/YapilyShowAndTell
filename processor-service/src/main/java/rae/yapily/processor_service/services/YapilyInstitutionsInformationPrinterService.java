package rae.yapily.processor_service.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class YapilyInstitutionsInformationPrinterService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @RabbitListener(queues = "${yapily.queue}")
    public void receiveMessage(String message) {
        try {
            JsonNode json = objectMapper.readTree(message);
            String institutionName = json
                    .path("data")
                    .get(0)
                    .path("name")
                    .asText("(unknown)");

            System.out.println("✅ Institution: " + institutionName);
        } catch (Exception ex) {
            System.out.println("❌ Failed to parse message: " + ex.getMessage());
        }
    }
}
