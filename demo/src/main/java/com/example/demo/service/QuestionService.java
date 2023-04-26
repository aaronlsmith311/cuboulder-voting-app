package com.example.demo.service;

import com.example.demo.exceptions.BadRequestException;
import com.example.demo.model.AppUser;
import com.example.demo.model.Question;
import com.example.demo.model.QuestionVote;
import com.example.demo.model.request.QuestionCreateRequestBody;
import com.example.demo.model.response.QuestionsResponseBody;
import com.example.demo.repository.AppUserRepository;
import com.example.demo.repository.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final AppUserRepository appUserRepository;

    public List<QuestionsResponseBody> getQuestions() {
        List<QuestionsResponseBody> questionsResponse = new ArrayList<>();
        List<Question> questions = questionRepository.findAll();

        questions.forEach(question -> {
            QuestionsResponseBody questionsResponseBody = new QuestionsResponseBody();
            questionsResponseBody.setQuestionId(question.getQuestionId());
            questionsResponseBody.setQuestionDescription(question.getQuestionDescription());
            questionsResponseBody.setCreatedTimestamp(question.getCreatedTimestamp());
            questionsResponseBody.setTotalVotes(question.getQuestionVotes().size());
            // count votes
            long yesVotes = question.getQuestionVotes().stream()
                    .filter(QuestionVote::isVote)
                    .count();
            long noVotes = question.getQuestionVotes().stream()
                    .filter(vote -> !vote.isVote())
                    .count();
            // calculate vote percentages
            if (questionsResponseBody.getTotalVotes() > 0) {
                questionsResponseBody.setYesVotesPercent((yesVotes / questionsResponseBody.getTotalVotes()) * 100 + "%");
                questionsResponseBody.setNoVotesPercent((noVotes / questionsResponseBody.getTotalVotes()) * 100 + "%");
            } else {
                questionsResponseBody.setYesVotesPercent("0%");
                questionsResponseBody.setNoVotesPercent("0%");
            }

            questionsResponse.add(questionsResponseBody);
        });
        return questionsResponse;
    }

    public Question createQuestion(QuestionCreateRequestBody request) {
        // make sure we have a valid user id
        AppUser appUser = appUserRepository.findById(request.getAppUserId())
                .orElseThrow(() -> new BadRequestException("No user found with id : " + request.getAppUserId()));

        // make sure new question is unique
        questionRepository.findByQuestionDescription(request.getQuestionDescription()).ifPresent(x -> {
            throw new BadRequestException("Question description must be unique");
        });

        // create new question
        Question newQuestion = new Question();
        newQuestion.setQuestionDescription(request.getQuestionDescription());
        newQuestion.setAppUser(appUser);
        newQuestion.setCreatedTimestamp(Instant.now());

        return questionRepository.save(newQuestion);
    }
}
