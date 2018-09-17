package ru.andrewquiz.service.quiz;

import org.springframework.stereotype.Component;
import ru.andrewquiz.dao.quiz.QuizEntity;
import ru.andrewquiz.dao.quiz.SuitEntity;
import ru.andrewquiz.dto.quiz.Suit;
import ru.andrewquiz.rest.exception.IllegalDeletionException;
import ru.andrewquiz.service.Validator;

/**
 * Created by Andrew on 15.04.2017.
 */

@Component
public class SuitValidator implements Validator<Suit, SuitEntity> {

    @Override
    public void validateReferentialIntegrity(SuitEntity suitEntity) {

        IllegalDeletionException e = null;

        for (QuizEntity quizEntity : suitEntity.getQuizes()) {
            if (e == null) {
                e = new IllegalDeletionException();
            }

            e.addDependentObject("quiz", quizEntity.getId(), quizEntity.getName());
        }

        if (e != null) {
            throw e;
        }
    }
}
