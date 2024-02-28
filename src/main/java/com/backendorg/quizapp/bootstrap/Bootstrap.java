package com.backendorg.quizapp.bootstrap;

import com.backendorg.quizapp.domain.Category;
import com.backendorg.quizapp.domain.Difficulty;
import com.backendorg.quizapp.domain.Question;
import com.backendorg.quizapp.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class Bootstrap implements CommandLineRunner {

    public static final int QUESTIONS_COUNT = 50;
    public static final int OPTIONS_COUNT_PER_QUESTION = 4;

    private final QuestionRepository questionRepository;

    @Override
    public void run(String... args) {
        loadQuestionsToDatabase();
    }

    private void loadQuestionsToDatabase() {
        if(questionRepository.count() > 0) return;

        List<Question> questions = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("questions.txt"))) {
            List<String> currentChunk = new ArrayList<>();
            String line;
            int linesRead = 0;
            while ((line = reader.readLine()) != null) {
                currentChunk.add(line.trim());
                linesRead++;
                if (linesRead == 10) {
                    String questionTitle = currentChunk.get(2);
                    String rightAnswerLetter =
                            currentChunk.get(7).replace("Correct Answer: ", "");
                    String rightAnswer = null;
                    Set<String> fullOptions = new HashSet<>();
                    for (int i = 3; i <= 6; i++) {
                        if(currentChunk.get(i).startsWith(rightAnswerLetter)) {
                            rightAnswer = currentChunk.get(i);
                        }
                        fullOptions.add(currentChunk.get(i));
                    }
                    Category category = Category.valueOf(
                            currentChunk.get(8).replace("Category: ", "")
                    );
                    Difficulty difficulty = Difficulty.valueOf(
                            currentChunk.get(9).replace("Difficulty: ", "")
                    );

                    questions.add(
                            Question.builder()
                                    .questionTitle(questionTitle)
                                    .options(fullOptions)
                                    .rightAnswer(rightAnswer)
                                    .category(category)
                                    .difficulty(difficulty)
                                    .build()
                    );

                    currentChunk.clear();
                    linesRead = 0;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        questionRepository.saveAll(questions);
    }
}
