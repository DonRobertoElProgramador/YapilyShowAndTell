package rae.yapily.authorization_service.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class YapilyInstitutionsInformationRetrievalService {

    @Value("${yapily.api-url}")
    private String yapilyApiUrl;

    @Value("${YAPILY_APP_ID}")
    private String appId;

    @Value("${YAPILY_APP_SECRET}")
    private String appSecret;

    private final RestTemplate restTemplate = new RestTemplate();

    public String getInstitutions() {

        String url = yapilyApiUrl + "/institutions";

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(appId, appSecret);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    requestEntity,
                    String.class
            );
            return response.getBody();
        } catch (HttpClientErrorException e) {
            return "Error calling Yapily API: " + e.getStatusCode() + " - " + e.getResponseBodyAsString();
        } catch (Exception e) {
            return "Unexpected error: " + e.getMessage();
        }
    }
}
