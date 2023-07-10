package com.emirhanbaran.quizapp.repos;

import com.emirhanbaran.quizapp.models.Question;
import com.emirhanbaran.quizapp.models.QuestionWrapper;
import com.emirhanbaran.quizapp.models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz,Long> {

}
