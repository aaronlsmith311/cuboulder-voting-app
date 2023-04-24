package com.example.demo.service;

import com.example.demo.exceptions.BadRequestException;
import com.example.demo.model.AppUser;
import com.example.demo.model.Question;
import com.example.demo.model.QuestionVote;
import com.example.demo.model.request.QuestionVoteRequestBody;
import com.example.demo.repository.AppUserRepository;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.repository.QuestionVoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class QuestionVoteService {
    private final QuestionVoteRepository questionVoteRepository;
    private final QuestionRepository questionRepository;
    private final AppUserRepository appUserRepository;

    public void questionVote(Long questionId, QuestionVoteRequestBody request) {
        // make sure we have a valid question id
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new BadRequestException("No question found with id : " + questionId));

        // make sure we have a valid user id
        AppUser appUser = appUserRepository.findById(request.getAppUserId())
                .orElseThrow(() -> new BadRequestException("No user found with id : " + request.getAppUserId()));

        // make sure user hasn't already voted on this question
        questionVoteRepository.findByQuestionQuestionIdAndAppUserUserId(
                question.getQuestionId(), appUser.getUserId())
                .ifPresent(x -> {
                    throw new BadRequestException("User has already voted on this question");
                });

        // create new vote and save question
        QuestionVote newQuestionVote = new QuestionVote();
        newQuestionVote.setQuestion(question);
        newQuestionVote.setAppUser(appUser);
        newQuestionVote.setVote(request.isQuestionVote());
        List<QuestionVote> votes = question.getQuestionVotes();
        votes.add(newQuestionVote);
        question.setQuestionVotes(votes);

        questionRepository.save(question);
    }
}
