package com.emirhanbaran.quizapp.services;

import com.emirhanbaran.quizapp.models.Question;
import com.emirhanbaran.quizapp.models.QuestionWrapper;
import com.emirhanbaran.quizapp.models.Quiz;
import com.emirhanbaran.quizapp.models.Response;
import com.emirhanbaran.quizapp.repos.QuestionRepository;
import com.emirhanbaran.quizapp.repos.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;

    public ResponseEntity<Quiz> createQuiz(String category, int numberOfQuestion, String title) {
        List<Question> questionList =questionRepository.findRandomQuestionsByCategory(category,numberOfQuestion);
        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questionList);
        return new ResponseEntity<>(quizRepository.save(quiz), HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizById(Long quizId) {
        Optional<Quiz> quiz=quizRepository.findById(quizId);
        List<Question> questionsFromDB=quiz.get().getQuestions();
        List<QuestionWrapper> questionForUser=new ArrayList<>();
        questionToQuestionWrapper(questionsFromDB,questionForUser);
        return new ResponseEntity<>(questionForUser,HttpStatus.OK);
    }

    private List<QuestionWrapper> questionToQuestionWrapper(List<Question> questionsFromDB, List<QuestionWrapper> questionForUser) {
        for(Question q: questionsFromDB){
            QuestionWrapper qW= new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionForUser.add(qW);
        }
        return questionForUser;
    }

    public ResponseEntity<Integer> calculateResult(Long quizId,List<Response> responses) {
            Quiz quiz= quizRepository.findById(quizId).get();
            List<Question> questions=quiz.getQuestions();
            int right=0;
            int i=0;
            for (Response response:responses ){
                if(response.getResponse().equals(questions.get(i).getRightAnswer())) {
                    right+=1;
                }
                i++;
            }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }

}
