package ru.andrewquiz.service.mapper.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.andrewquiz.dao.quiz.CategoryEntity;
import ru.andrewquiz.dao.quiz.SuitEntity;
import ru.andrewquiz.dto.quiz.Suit;
import ru.andrewquiz.repository.quiz.CategoryRepository;
import ru.andrewquiz.rest.exception.EntityNotFoundException;
import ru.andrewquiz.service.mapper.AbstractMapper;

/**
 * Created by Andrew on 23.04.2017.
 */

@Component
public class SuitDtoToEntityMapper extends AbstractMapper<Suit, SuitEntity> {

    private CategoryRepository categoryRepo;

    @Autowired
    public SuitDtoToEntityMapper(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    protected SuitEntity performMapping(Suit src) {
        return performMapping(src, new SuitEntity());
    }

    @Override
    protected SuitEntity performMapping(Suit src, SuitEntity dst) {

        dst.setName(src.getName());

        if (src.getCategoryId() == null) {
            dst.setCategory(null);
        } else {
            CategoryEntity category = categoryRepo.findOne(src.getCategoryId());
            if (category == null) {
                throw new EntityNotFoundException(CategoryEntity.class, src.getCategoryId());
            }
            dst.setCategory(category);
        }

        return dst;
    }
}
