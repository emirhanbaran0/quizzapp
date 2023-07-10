package com.emirhanbaran.quizapp.controllers;

import com.emirhanbaran.quizapp.models.Question;
import com.emirhanbaran.quizapp.models.QuestionWrapper;
import com.emirhanbaran.quizapp.models.Quiz;
import com.emirhanbaran.quizapp.models.Response;
import com.emirhanbaran.quizapp.services.QuizService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;
    @PostMapping("/create")
    public ResponseEntity<Quiz> createQuiz(@RequestParam String category, @RequestParam int numberOfQuestion, @RequestParam String title){
        return  quizService.createQuiz(category,numberOfQuestion,title);
    }

    @GetMapping("/{quizId}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable long quizId){
        return quizService.getQuizById(quizId);
    }

    @PostMapping("/submit/{quizId}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Long quizId, @RequestBody List<Response> responses){
        return quizService.calculateResult(quizId,responses);
    }
}
