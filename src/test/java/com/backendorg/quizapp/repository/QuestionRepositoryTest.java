package com.backendorg.quizapp.repository;

import com.backendorg.quizapp.bootstrap.Bootstrap;
import com.backendorg.quizapp.domain.Question;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static com.backendorg.quizapp.bootstrap.Bootstrap.OPTIONS_COUNT_PER_QUESTION;
import static com.backendorg.quizapp.bootstrap.Bootstrap.QUESTIONS_COUNT;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Import({Bootstrap.class})
class QuestionRepositoryTest {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    void testDeleteAllQuestionsDeleteAllOptions() {
        Query optionsQuery = entityManager.createNativeQuery("SELECT COUNT(*) FROM question_options");
        int totalOptionsCountBeforeDeletion = ((Number) optionsQuery.getSingleResult()).intValue();

        // Verify that current count
        assertEquals(QUESTIONS_COUNT, questionRepository.count());
        assertEquals(QUESTIONS_COUNT*OPTIONS_COUNT_PER_QUESTION, totalOptionsCountBeforeDeletion);

        // Delete all questions
        questionRepository.deleteAll();

        int totalOptionsCountAfterDeletion = ((Number) optionsQuery.getSingleResult()).intValue();

        // Verify that there are no remaining questions and options
        assertEquals(0, questionRepository.count());
        assertEquals(0, totalOptionsCountAfterDeletion);
    }

    @Test
    void testDeleteSingleQuestionDeletesOptions() {
        Question randomQuestion = questionRepository.findAll().get(0);

        Query optionsQuery = entityManager.createNativeQuery(
                "SELECT COUNT(*) FROM question_options WHERE question_id = :questionId");
        optionsQuery.setParameter("questionId", randomQuestion.getQuestionId());
        int questionOptionsCountBeforeDeletion = ((Number) optionsQuery.getSingleResult()).intValue();

        // Verify that current count
        assertEquals(OPTIONS_COUNT_PER_QUESTION, questionOptionsCountBeforeDeletion);

        // Delete all questions
        questionRepository.delete(randomQuestion);

        int questionOptionsCountAfterDeletion = ((Number) optionsQuery.getSingleResult()).intValue();

        // Verify that there are no remaining questions and options
        assertEquals(QUESTIONS_COUNT-1, questionRepository.count());
        assertEquals(0, questionOptionsCountAfterDeletion);
    }

}