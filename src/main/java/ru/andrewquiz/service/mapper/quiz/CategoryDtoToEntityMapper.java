package ru.andrewquiz.service.mapper.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.andrewquiz.dao.quiz.CategoryEntity;
import ru.andrewquiz.dto.quiz.Category;
import ru.andrewquiz.repository.quiz.CategoryRepository;
import ru.andrewquiz.service.mapper.AbstractMapper;

/**
 * Created by Andrew on 23.04.2017.
 */

@Component
public class CategoryDtoToEntityMapper extends AbstractMapper<Category, CategoryEntity> {

    private CategoryRepository repo;

    @Autowired
    public CategoryDtoToEntityMapper(CategoryRepository repo) {
        this.repo = repo;
    }

    @Override
    protected CategoryEntity performMapping(Category src) {
        return performMapping(src, new CategoryEntity());
    }

    @Override
    protected CategoryEntity performMapping(Category src, CategoryEntity dst) {

        dst.setDescription(src.getDescription());
        dst.setName(src.getName());
        dst.setParentCategory(src.getParentCategoryId() != null ? repo.findOne(src.getParentCategoryId()) : null); //TODO not found

        return dst;
    }
}
