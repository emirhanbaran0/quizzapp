package com.emirhanbaran.quizapp.models;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@AllArgsConstructor
public class Response {

    private Long id;
    private String response;

}
