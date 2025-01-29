package com.yolo.backend.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse {

    private int statusCode;
    private String body;

}
