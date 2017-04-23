package ru.andrewquiz.service.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import ru.andrewquiz.dao.quiz.CategoryEntity;
import ru.andrewquiz.dto.quiz.Category;
import ru.andrewquiz.repository.quiz.CategoryRepository;
import ru.andrewquiz.service.AbstractResourceService;
import ru.andrewquiz.service.Validator;
import ru.andrewquiz.service.mapper.AbstractMapper;
import ru.andrewquiz.service.mapper.quiz.CategoryDtoToEntityMapper;
import ru.andrewquiz.service.mapper.quiz.CategoryEntityToDtoMapper;

import java.util.List;

/**
 * Created by Andrew on 02.04.2017.
 */

@Service
public class CategoryService extends AbstractResourceService<Category, CategoryEntity> {

    private CategoryRepository repo;

    private CategoryDtoToEntityMapper dtoToEntityMapper;

    private CategoryEntityToDtoMapper entityToDtoMapper;

    private CategoryValidator validator;

    @Autowired
    public CategoryService(CategoryRepository repo, CategoryValidator validator,
                           CategoryDtoToEntityMapper dtoToEntityMapper, CategoryEntityToDtoMapper entityToDtoMapper) {

        this.repo = repo;
        this.validator = validator;
        this.entityToDtoMapper = entityToDtoMapper;
        this.dtoToEntityMapper = dtoToEntityMapper;
    }

    public List<Category> getCategoriesByParentCategoryId(Long parentCategoryId) {

        Iterable<CategoryEntity> categoryEntities = repo.findByParentCategoryId(parentCategoryId);

        List<Category> categories = entityToDtoMapper.mapList(categoryEntities);

        return categories;
    }

    @Override
    protected Class<Category> getDtoClass() {
        return Category.class;
    }

    @Override
    protected CrudRepository<CategoryEntity, Long> getRepo() {
        return repo;
    }

    @Override
    protected Validator<Category, CategoryEntity> getValidator() {
        return validator;
    }

    @Override
    protected AbstractMapper<Category, CategoryEntity> getDtoToEntityMapper() {
        return dtoToEntityMapper;
    }

    @Override
    protected AbstractMapper<CategoryEntity, Category> getEntityToDtoMapper() {
        return entityToDtoMapper;
    }
}
