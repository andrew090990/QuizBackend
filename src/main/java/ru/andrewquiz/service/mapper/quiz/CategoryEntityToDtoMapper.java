package ru.andrewquiz.service.mapper.quiz;

import org.springframework.stereotype.Component;
import ru.andrewquiz.dao.quiz.CategoryEntity;
import ru.andrewquiz.dto.quiz.Category;
import ru.andrewquiz.service.mapper.AbstractMapper;

/**
 * Created by Andrew on 23.04.2017.
 */

@Component
public class CategoryEntityToDtoMapper extends AbstractMapper<CategoryEntity, Category> {

    @Override
    protected Category performMapping(CategoryEntity src) {
        return performMapping(src, new Category());
    }

    @Override
    protected Category performMapping(CategoryEntity src, Category dst) {

        dst.setId(src.getId());
        dst.setCreatedAt(src.getCreatedAt());
        dst.setUpdatedAt(src.getUpdatedAt());
        dst.setDescription(src.getDescription());
        dst.setName(src.getName());
        dst.setParentCategoryId(src.getParentCategory() != null ? src.getParentCategory().getId() : null);

        return dst;
    }
}
