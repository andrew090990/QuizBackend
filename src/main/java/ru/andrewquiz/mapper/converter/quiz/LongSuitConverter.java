package ru.andrewquiz.mapper.converter.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.andrewquiz.mapper.converter.AbstractLongEntityConverter;
import ru.andrewquiz.repository.quiz.SuitRepository;

/**
 * Created by Andrew on 15.04.2017.
 */

@Component
public class LongSuitConverter extends AbstractLongEntityConverter {

    private SuitRepository repo;

    @Autowired
    public LongSuitConverter(SuitRepository repo) {
        this.repo = repo;
    }

    @Override
    protected Object convertLongToEntity(Long id) {
        return repo.findOne(id);
    }
}
