package com.du6ak.backend.dtos;

import lombok.Data;

@Data
public class SigninRequest {
    private String username;
    private String password;
}
