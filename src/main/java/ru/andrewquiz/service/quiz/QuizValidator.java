package ru.andrewquiz.service.quiz;

import org.springframework.stereotype.Component;
import ru.andrewquiz.dao.quiz.QuizEntity;
import ru.andrewquiz.dto.quiz.Quiz;
import ru.andrewquiz.service.Validator;

/**
 * Created by Andrew on 16.04.2017.
 */

@Component
public class QuizValidator implements Validator<Quiz, QuizEntity> {

    @Override
    public void validateReferentialIntegrity(QuizEntity quizEntity) {

        return;
    }
}
