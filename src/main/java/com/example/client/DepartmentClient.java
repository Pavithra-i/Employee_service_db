package com.example.client;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Component
public class DepartmentClient {

    private final RestClient restClient;

    public DepartmentClient(RestClient.Builder builder) {
        this.restClient = builder
                .baseUrl("http://localhost:8081")
                .build();
    }

    public Map<?, ?> getDepartmentById(Long departmentId) {
        try {
            Map<?, ?> department = restClient.get()
                    .uri("/departments/{id}", departmentId)
                    .retrieve()
                    .body(Map.class);

            if (department == null) {
                throw new IllegalStateException("Department service returned an empty response");
            }

            return department;
        } catch (RestClientException ex) {
            throw new IllegalArgumentException(
                    "Unable to find department with ID: " + departmentId);
        }
    }
}
