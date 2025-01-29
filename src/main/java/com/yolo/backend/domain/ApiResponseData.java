package com.yolo.backend.domain;

import com.yolo.backend.dtos.UserDTO;
import com.yolo.backend.dtos.UserDTOInitialDb;

import java.util.List;

public class ApiResponseData {

    private List<UserDTOInitialDb> clientes;

    public List<UserDTOInitialDb> getClientes() {
        return clientes;
    }

    public void setClientes(List<UserDTOInitialDb> clientes) {
        this.clientes = clientes;
    }
}
