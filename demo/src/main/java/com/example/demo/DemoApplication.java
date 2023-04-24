package com.example.demo;

import com.example.demo.model.AppUser;
import com.example.demo.model.Question;
import com.example.demo.repository.AppUserRepository;
import com.example.demo.repository.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Instant;
import java.util.Arrays;

@SpringBootApplication
@AllArgsConstructor
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner init(AppUserRepository appUserRepository, QuestionRepository questionRepository) {
		// create admin user for login
		AppUser user = new AppUser();
		user.setUsername("admin");
		user.setPassword("admin");
		appUserRepository.save(user);
		// create initial questions
		return args -> {
			Question question1 = new Question();
			question1.setQuestionDescription("Do you like Java?");
			question1.setAppUser(user);
			question1.setCreatedTimestamp(Instant.now());

			Question question2 = new Question();
			question2.setQuestionDescription("Do you like Angular?");
			question2.setAppUser(user);
			question2.setCreatedTimestamp(Instant.now());

			questionRepository.saveAll(Arrays.asList(question1, question2));
		};
	}

}
