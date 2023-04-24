package com.example.demo.controller;

import com.example.demo.model.Question;
import com.example.demo.model.request.QuestionCreateRequestBody;
import com.example.demo.model.request.QuestionVoteRequestBody;
import com.example.demo.model.response.QuestionsResponseBody;
import com.example.demo.service.QuestionService;
import com.example.demo.service.QuestionVoteService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class QuestionController {
    private final QuestionService questionService;
    private final QuestionVoteService questionVoteService;

    @GetMapping("/questions")
    public List<QuestionsResponseBody> getQuestions() {
        return questionService.getQuestions();
    }

    @PostMapping("/questions")
    public ResponseEntity<Object> createQuestion(@RequestBody QuestionCreateRequestBody request) {
        Question question = questionService.createQuestion(request);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                .buildAndExpand(question.getQuestionId()).toUri())
                .build();
    }

    @PutMapping("questions/vote/{id}")
    public ResponseEntity<Object> questionVote(@RequestBody QuestionVoteRequestBody request, @PathVariable Long id) {
        questionVoteService.questionVote(id, request);
        return ResponseEntity.ok().build();
    }
}
