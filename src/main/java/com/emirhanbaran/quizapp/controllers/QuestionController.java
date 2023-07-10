package com.emirhanbaran.quizapp.controllers;

import com.emirhanbaran.quizapp.models.Question;
import com.emirhanbaran.quizapp.requests.UpdateQuestion;
import com.emirhanbaran.quizapp.services.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){

        return  questionService.getAllQuesitons();
    }

    @GetMapping("category/{categoryName}")
    public ResponseEntity<List<Question>> getAllQuestionsByCategory(@PathVariable String categoryName){
        return questionService.getAllQuesitonsByCategory(categoryName);
    }

    @PostMapping
    public ResponseEntity<Question> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

    @DeleteMapping("/{questionId}")
    public void deleteQuestion(@PathVariable Long questionId){
        questionService.deleteQuestion(questionId);
    }

    @PutMapping("/{questionId}")
    public Question updateQuestion(@PathVariable Long questionId, @RequestBody UpdateQuestion updateQuestion){
        return  questionService.updateQuestion(questionId,updateQuestion);
    }
}
