package ru.andrewquiz.service.quiz;

import org.springframework.stereotype.Component;
import ru.andrewquiz.dao.quiz.SuitEntity;
import ru.andrewquiz.dto.quiz.Suit;
import ru.andrewquiz.service.Validator;

/**
 * Created by Andrew on 15.04.2017.
 */

@Component
public class SuitValidator implements Validator<Suit, SuitEntity> {

    @Override
    public void validateReferentialIntegrity(SuitEntity suitEntity) {

        return;
    }
}
