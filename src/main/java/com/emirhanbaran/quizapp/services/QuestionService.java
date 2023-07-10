package com.emirhanbaran.quizapp.services;

import com.emirhanbaran.quizapp.models.Question;
import com.emirhanbaran.quizapp.repos.QuestionRepository;
import com.emirhanbaran.quizapp.requests.UpdateQuestion;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private  final QuestionRepository questionRepository;

    public ResponseEntity<List<Question>> getAllQuesitons() {
       try {
           return  new ResponseEntity<>(questionRepository.findAll(), HttpStatus.OK);
       }catch (Exception e){
           e.printStackTrace();
       }
        return  new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getAllQuesitonsByCategory(String categoryName) {
       try {
           return new ResponseEntity<>(questionRepository.findAllByCategory(categoryName),HttpStatus.OK);
       }catch (Exception e){
           e.printStackTrace();
       }
        return  new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Question> addQuestion(Question question) {
        try {
            return new ResponseEntity<>(questionRepository.save(question),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
    }

    public void deleteQuestion(Long questionId) {
        questionRepository.deleteById(questionId);
    }

    public Question updateQuestion(Long questionId, UpdateQuestion updateQuestion) {
        Optional<Question> question=questionRepository.findById(questionId);
        if(question.isPresent()){
            return  questionUpdateService(question.get(),updateQuestion);
        }
        //custom Exception
        return null;
    }

    public Question questionUpdateService(Question question,UpdateQuestion updateQuestion){
        question.setQuestionTitle(updateQuestion.getQuestionTitle());
        question.setCategory(updateQuestion.getCategory());
        question.setDifficultyLevel(updateQuestion.getDifficultyLevel());
        question.setOption1(updateQuestion.getOption1());
        question.setOption2(updateQuestion.getOption2());
        question.setOption3(updateQuestion.getOption3());
        question.setOption4(updateQuestion.getOption4());
        question.setRightAnswer(updateQuestion.getRightAnswer());
        return question;
    }
}
