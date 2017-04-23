package ru.andrewquiz.service.mapper.quiz;

import org.springframework.stereotype.Component;
import ru.andrewquiz.dao.quiz.SuitEntity;
import ru.andrewquiz.dto.quiz.Suit;
import ru.andrewquiz.service.mapper.AbstractMapper;

/**
 * Created by Andrew on 23.04.2017.
 */

@Component
public class SuitEntityToDtoMapper extends AbstractMapper<SuitEntity, Suit> {

    @Override
    protected Suit perfomMapping(SuitEntity src) {
        return perfomMapping(src, new Suit());
    }

    @Override
    protected Suit perfomMapping(SuitEntity src, Suit dst) {

        dst.setId(src.getId());
        dst.setCreatedAt(src.getCreatedAt());
        dst.setUpdatedAt(src.getUpdatedAt());
        dst.setName(src.getName());
        dst.setCategoryId(src.getCategory() != null ? src.getCategory().getId() : null);

        return dst;
    }
}
