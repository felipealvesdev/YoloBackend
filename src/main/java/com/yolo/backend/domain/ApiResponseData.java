package com.yolo.backend.domain;

import com.yolo.backend.dtos.UserDTO;

import java.util.List;

public class ApiResponseData {

    private List<UserDTO> clientes;

    public List<UserDTO> getClientes() {
        return clientes;
    }

    public void setClientes(List<UserDTO> clientes) {
        this.clientes = clientes;
    }
}
