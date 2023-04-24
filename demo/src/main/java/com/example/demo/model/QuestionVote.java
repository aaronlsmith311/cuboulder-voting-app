package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "QUESTION_VOTE")
@Getter
@Setter
public class QuestionVote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long questionVoteId;
    private boolean vote;
    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    private Question question;
    @OneToOne
    @JoinColumn(name = "APP_USER_ID")
    private AppUser appUser;
}
