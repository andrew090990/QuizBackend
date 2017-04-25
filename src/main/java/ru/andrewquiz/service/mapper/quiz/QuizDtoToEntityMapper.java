package ru.andrewquiz.service.mapper.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.andrewquiz.dao.quiz.QuizEntity;
import ru.andrewquiz.dto.quiz.Quiz;
import ru.andrewquiz.repository.quiz.SuitRepository;
import ru.andrewquiz.service.mapper.AbstractMapper;

/**
 * Created by Andrew on 23.04.2017.
 */

@Component
public class QuizDtoToEntityMapper extends AbstractMapper<Quiz, QuizEntity> {

    private SuitRepository suitRepo;

    @Autowired
    public QuizDtoToEntityMapper(SuitRepository suitRepo) {
        this.suitRepo = suitRepo;
    }

    @Override
    protected QuizEntity performMapping(Quiz src) {
        return performMapping(src, new QuizEntity());
    }

    @Override
    protected QuizEntity performMapping(Quiz src, QuizEntity dst) {

        dst.setName(src.getName());
        dst.setTypeId(src.getType() != null ? src.getType().id() : null);
        dst.setSuit(src.getSuitId() != null ? suitRepo.findOne(src.getSuitId()) : null);

        return dst;
    }
}
