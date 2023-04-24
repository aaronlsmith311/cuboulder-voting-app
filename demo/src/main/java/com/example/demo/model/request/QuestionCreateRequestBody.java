package com.example.demo.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class QuestionCreateRequestBody {
    @NotBlank(message = "Question Description is required")
    @Size(max = 200)
    private String questionDescription;
    @NotNull(message = "App User Id is required")
    private Long appUserId;
}
