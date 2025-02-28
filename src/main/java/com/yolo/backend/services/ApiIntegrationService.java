package com.yolo.backend.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yolo.backend.domain.ApiResponse;
import com.yolo.backend.domain.ApiResponseData;
import com.yolo.backend.domain.User;
import com.yolo.backend.dtos.UserDTO;
import com.yolo.backend.dtos.UserDTOInitialDb;
import com.yolo.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class ApiIntegrationService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;


    public void getInitialDataFromApi() {
        if (userRepository.count() > 0) {
            System.out.println("Initial data has already been initialized. Skipping getInitialDataFromApi...");
            return;
        }

        String apiUrl = "https://3ji5haxzr9.execute-api.us-east-1.amazonaws.com/dev/caseYolo";

        try {

            ApiResponse apiResponse = restTemplate.getForObject(apiUrl, ApiResponse.class);

            if (apiResponse != null && apiResponse.getBody() != null) {

                String body = apiResponse.getBody();
                ApiResponseData parsedBody = objectMapper.readValue(body, ApiResponseData.class);


                for (UserDTOInitialDb userDTOInitialDb : parsedBody.getClientes()) {
                    User user = new User();
                    user.setName(userDTOInitialDb.name());
                    user.setEmail(userDTOInitialDb.email());
                    user.setPhoneNumber(userDTOInitialDb.phoneNumber());
                    user.setUserType(userDTOInitialDb.userType());

                    if(userDTOInitialDb.createdAt() != null) {
                        user.setCreatedAt(LocalDate.parse(userDTOInitialDb.createdAt().toString()));

                    }

                    userRepository.save(user);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
