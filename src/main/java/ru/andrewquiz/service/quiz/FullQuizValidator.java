package ru.andrewquiz.service.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.andrewquiz.dao.quiz.FullQuizEntity;
import ru.andrewquiz.dto.quiz.FullQuiz;
import ru.andrewquiz.service.Validator;

/**
 * Created by Andrew on 16.04.2017.
 */

@Component
public class FullQuizValidator implements Validator<FullQuiz, FullQuizEntity> {

    private QuizValidator quizValidator;

    @Autowired
    public FullQuizValidator(QuizValidator quizValidator) {
        this.quizValidator = quizValidator;
    }

    @Override
    public void validateReferentialIntegrity(FullQuizEntity fullQuizEntity) {

        quizValidator.validateReferentialIntegrity(fullQuizEntity);
    }
}
