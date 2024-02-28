package com.backendorg.quizapp.service;

import com.backendorg.quizapp.domain.Category;
import com.backendorg.quizapp.domain.Difficulty;
import com.backendorg.quizapp.mapper.QuestionMapper;
import com.backendorg.quizapp.model.QuestionDTO;
import com.backendorg.quizapp.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    @Override
    public List<QuestionDTO> getAllQuestions() {
        return questionRepository.findAll().stream().map(questionMapper::entityToDTO).toList();
    }

    @Override
    public List<QuestionDTO> searchQuestions(Category category, Difficulty difficulty) {
        if (category == null && difficulty == null) {
            return getAllQuestions();
        }
        if(category == null) {
            return questionRepository.findAllByDifficulty(difficulty)
                    .stream().map(questionMapper::entityToDTO).toList();
        }
        if(difficulty == null) {
            return questionRepository.findAllByCategory(category)
                    .stream().map(questionMapper::entityToDTO).toList();
        }
        return questionRepository.findAllByCategoryAndDifficulty(category, difficulty)
                .stream().map(questionMapper::entityToDTO).toList();
    }
}
