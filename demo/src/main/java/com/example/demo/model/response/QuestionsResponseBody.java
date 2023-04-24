package com.example.demo.model.response;

import lombok.Data;

import java.time.Instant;

@Data
public class QuestionsResponseBody {
    private Long questionId;
    private String questionDescription;
    private int totalVotes;
    private String yesVotesPercent;
    private String noVotesPercent;
    private Instant createdTimestamp;
}
