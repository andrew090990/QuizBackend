package ru.andrewquiz.service.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import ru.andrewquiz.dao.quiz.CategoryEntity;
import ru.andrewquiz.dto.quiz.Category;
import ru.andrewquiz.mapper.CustomDozerBeanMapper;
import ru.andrewquiz.repository.quiz.CategoryRepository;
import ru.andrewquiz.service.AbstractResourceService;
import ru.andrewquiz.service.Validator;

import java.util.List;

/**
 * Created by Andrew on 02.04.2017.
 */

@Service
public class CategoryService extends AbstractResourceService<Category, CategoryEntity, Long> {

    private CategoryRepository repo;

    private CustomDozerBeanMapper mapper;

    private CategoryValidator validator;

    @Autowired
    public CategoryService(CategoryRepository repo, CustomDozerBeanMapper mapper, CategoryValidator validator) {

        this.repo = repo;
        this.mapper = mapper;
        this.validator = validator;
    }

    public List<Category> getCategoriesByParentCategoryId(Long parentCategoryId) {

        Iterable<CategoryEntity> categoryEntities = repo.findByParentCategoryId(parentCategoryId);

        List<Category> categories = mapper.mapList(categoryEntities, Category.class);

        return categories;
    }

    @Override
    protected Class<Category> getDtoClass() {
        return Category.class;
    }

    @Override
    protected Class<CategoryEntity> getEntityClass() {
        return CategoryEntity.class;
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
    protected CustomDozerBeanMapper getMapper() {
        return mapper;
    }
}
