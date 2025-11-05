package rae.yapily.authorization_service.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rae.yapily.authorization_service.services.RabbitMQQueueWriterService;
import rae.yapily.authorization_service.services.YapilyInstitutionsInformationRetrievalService;

@RestController
@RequestMapping("/api")
public class YapilyInstitutionsInformationRetrievalController {

    private final YapilyInstitutionsInformationRetrievalService yapilyInstitutionsInformationRetrievalService;
    private final RabbitMQQueueWriterService rabbitMQQueueWriterService;

    public YapilyInstitutionsInformationRetrievalController(YapilyInstitutionsInformationRetrievalService yapilyInstitutionsInformationRetrievalService, RabbitMQQueueWriterService rabbitMQQueueWriterService) {
        this.yapilyInstitutionsInformationRetrievalService = yapilyInstitutionsInformationRetrievalService;
        this.rabbitMQQueueWriterService = rabbitMQQueueWriterService;
    }

    @PostMapping("/forward")
    public String forward() {
        String data = yapilyInstitutionsInformationRetrievalService.getInstitutions();
        rabbitMQQueueWriterService.send(data);
        return "Data sent to queue: ";
    }
}


