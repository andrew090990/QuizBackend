package ru.andrewquiz.service.mapper.quiz;

import org.springframework.stereotype.Component;
import ru.andrewquiz.dao.quiz.QuizEntity;
import ru.andrewquiz.dto.quiz.Quiz;
import ru.andrewquiz.dto.quiz.QuizType;
import ru.andrewquiz.service.mapper.AbstractMapper;

/**
 * Created by Andrew on 23.04.2017.
 */

@Component
public class QuizEntityToDtoMapper extends AbstractMapper<QuizEntity, Quiz> {

    @Override
    protected Quiz performMapping(QuizEntity src) {
        return performMapping(src, new Quiz());
    }

    @Override
    protected Quiz performMapping(QuizEntity src, Quiz dst) {

        dst.setName(src.getName());
        dst.setId(src.getId());
        dst.setCreatedAt(src.getCreatedAt());
        dst.setUpdatedAt(src.getUpdatedAt());
        dst.setType(QuizType.fromId(src.getTypeId()));
        dst.setSuitId(src.getSuit() != null ? src.getSuit().getId() : null);

        return dst;
    }
}
