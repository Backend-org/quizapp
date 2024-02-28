package com.backendorg.quizapp.controller;

import com.backendorg.quizapp.domain.Category;
import com.backendorg.quizapp.domain.Difficulty;
import com.backendorg.quizapp.model.QuestionDTO;
import com.backendorg.quizapp.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("allQuestions")
    public List<QuestionDTO> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("search")
    public List<QuestionDTO> searchQuestions(
            @RequestParam(value = "category", required = false) Category category,
            @RequestParam(value = "difficulty", required = false) Difficulty difficulty
    ) {
        return questionService.searchQuestions(category, difficulty);
    }

}
