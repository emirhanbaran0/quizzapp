package com.emirhanbaran.quizapp.requests;

import lombok.Data;

@Data
public class UpdateQuestion {
    private  String questionTitle;
    private  String category;
    private  String option1;
    private  String option2;
    private  String option3;
    private  String option4;
    private  String rightAnswer;

    private String difficultyLevel;
}
