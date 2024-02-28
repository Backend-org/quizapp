package com.backendorg.quizapp.mapper;

import com.backendorg.quizapp.domain.Question;
import com.backendorg.quizapp.model.QuestionDTO;
import org.mapstruct.Mapper;

@Mapper
public interface QuestionMapper extends BaseMapper<Question, QuestionDTO> {
}
