package com.backendorg.quizapp.model;

import com.backendorg.quizapp.domain.Category;
import com.backendorg.quizapp.domain.Difficulty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDTO {

    private String questionId;

    private String questionTitle;

    private Set<String> options = new HashSet<>();

    private String rightAnswer;

    private Difficulty difficulty;

    private Category category;

}
