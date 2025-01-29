package com.yolo.backend.runner;

import com.yolo.backend.services.ApiIntegrationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final ApiIntegrationService apiIntegrationService;

    public DataLoader(ApiIntegrationService apiIntegrationService) {
        this.apiIntegrationService = apiIntegrationService;
    }

    @Override
    public void run(String... args) throws Exception {
        apiIntegrationService.getInitialDataFromApi();
    }
}
