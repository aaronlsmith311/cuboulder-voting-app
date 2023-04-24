package com.example.demo.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class QuestionVoteRequestBody {
    @NotNull
    private Long appUserId;
    private Long questionId;
    private boolean questionVote;
}
