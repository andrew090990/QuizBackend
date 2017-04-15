package ru.andrewquiz.mapper.converter.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.andrewquiz.mapper.converter.AbstractLongEntityConverter;
import ru.andrewquiz.repository.quiz.CategoryRepository;

/**
 * Created by Andrew on 12.04.2017.
 */

@Component
public class LongCategoryConverter extends AbstractLongEntityConverter {

    @Autowired
    private CategoryRepository repo;

//    @Autowired
//    public LongCategoryConverter(CategoryRepository repo) {
//        this.repo = repo;
//    }

    @Override
    protected Object convertLongToEntity(Long id) {
        return repo.findOne(id);
    }
}
