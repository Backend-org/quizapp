package com.backendorg.quizapp.repository;

import com.backendorg.quizapp.domain.Category;
import com.backendorg.quizapp.domain.Difficulty;
import com.backendorg.quizapp.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, String> {

    List<Question> findAllByCategory(Category category);

    List<Question> findAllByDifficulty(Difficulty difficulty);

    List<Question> findAllByCategoryAndDifficulty(Category category, Difficulty difficulty);

}
