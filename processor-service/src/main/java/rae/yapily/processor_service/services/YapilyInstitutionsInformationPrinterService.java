package rae.yapily.processor_service.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class YapilyInstitutionsInformationPrinterService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @RabbitListener(queues = "${yapily.queue}")
    public void receiveMessage(String message) {
        try {
            JsonNode json = objectMapper.readTree(message);
            JsonNode data = json.path("data");
            List<String> names = new ArrayList<>();

            for (JsonNode institution : data) {
                names.add(institution.path("name").asText("(unknown)"));
            }

            String institutionNames = String.join(", ", names);

            System.out.println("Institution names: " + institutionNames);
        } catch (Exception ex) {
            System.out.println("Failed whe retrieving institutions");
        }
    }
}
