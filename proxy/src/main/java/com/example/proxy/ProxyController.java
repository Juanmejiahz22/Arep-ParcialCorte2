package com.example.proxy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.Map;

@RestController
public class ProxyController {

    @Value("${mathservice.instance1}")
    private String instance1;

    @Value("${mathservice.instance2}")
    private String instance2;

    private final RestClient restClient = RestClient.create();

    @GetMapping("/fibwin")
    public Map<String, Object> fibwin(@RequestParam int value) {
        // Intentar con la instancia activa (instance1)
        try {
            String url = instance1 + "/fibwin?value=" + value;
            return restClient.get()
                    .uri(url)
                    .retrieve()
                    .body(new ParameterizedTypeReference<Map<String, Object>>() {});
        } catch (Exception e) {
            System.out.println("Instancia activa no disponible, usando instancia pasiva: " + e.getMessage());
        }

        // Si la activa falla, usar la pasiva (instance2)
        String url = instance2 + "/fibwin?value=" + value;
        return restClient.get()
                .uri(url)
                .retrieve()
                .body(new ParameterizedTypeReference<Map<String, Object>>() {});
    }
}
