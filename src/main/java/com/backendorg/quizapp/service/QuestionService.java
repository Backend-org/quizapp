package com.backendorg.quizapp.service;

import com.backendorg.quizapp.domain.Category;
import com.backendorg.quizapp.domain.Difficulty;
import com.backendorg.quizapp.model.QuestionDTO;

import java.util.List;

public interface QuestionService {

    List<QuestionDTO> getAllQuestions();

    List<QuestionDTO> searchQuestions(Category category, Difficulty difficulty);
}
