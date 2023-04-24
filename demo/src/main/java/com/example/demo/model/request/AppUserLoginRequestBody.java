package com.example.demo.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AppUserLoginRequestBody {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
