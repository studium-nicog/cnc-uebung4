package org.example.aufgabe4.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RestController
public class TestController {
    private final RestTemplate restTemplate;

    public TestController(@Autowired RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public String getCommitMessage() {
        var request = new RequestEntity<>(HttpMethod.GET, URI.create("https://whatthecommit.com/index.txt"));
        var response = restTemplate.exchange(request, String.class);

        return "Your personalized git commit message is: \"" + response.getBody() + "\"";
    }
}
