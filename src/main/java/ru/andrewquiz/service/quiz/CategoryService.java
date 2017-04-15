package ru.andrewquiz.service.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.andrewquiz.dao.quiz.CategoryEntity;
import ru.andrewquiz.dao.quiz.SuitEntity;
import ru.andrewquiz.dto.quiz.Category;
import ru.andrewquiz.mapper.CustomDozerBeanMapper;
import ru.andrewquiz.repository.quiz.CategoryRepository;
import ru.andrewquiz.rest.exception.EntityNotFoundException;
import ru.andrewquiz.rest.exception.IllegalDeletionException;
import ru.andrewquiz.rest.exception.IllegalRequestException;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Andrew on 02.04.2017.
 */

@Service
public class CategoryService {

    private CategoryRepository repo;

    private CustomDozerBeanMapper mapper;

    @Autowired
    public CategoryService(CategoryRepository repo, CustomDozerBeanMapper mapper) {

        this.repo = repo;
        this.mapper = mapper;
    }

    public List<Category> getCategories() {

        Iterable<CategoryEntity> categoryEntities = repo.findAll();

        List<Category> categories = mapper.mapList(categoryEntities, Category.class);

        return categories;
    }

    public List<Category> getCategoriesByParentCategoryId(Long parentCategoryId) {

        Iterable<CategoryEntity> categoryEntities = repo.findByParentCategoryId(parentCategoryId);

        List<Category> categories = mapper.mapList(categoryEntities, Category.class);

        return categories;
    }

    public Category getCategory(Long id) {

        CategoryEntity categoryEntity = findCategoryEntity(id);

        Category category = mapper.map(categoryEntity, Category.class);

        return category;
    }

    public Long createCategory(Category category) {

        //TODO validation

        if (category.getId() != null) {
            throw new IllegalRequestException("Id must be null when posting new resource.");
        }

        CategoryEntity categoryEntity = mapper.map(category, CategoryEntity.class);

        categoryEntity.setCreatedAt(Calendar.getInstance());

        repo.save(categoryEntity);

        return categoryEntity.getId();
    }

    public void updateCategory(Category category, Long id) {

        //TODO validation

        if (!repo.exists(category.getId())) {
            throw new EntityNotFoundException(CategoryEntity.class,id);
        }

        CategoryEntity categoryEntity = mapper.map(category, CategoryEntity.class);

        categoryEntity.setId(id);

        categoryEntity.setUpdatedAt(Calendar.getInstance());

        repo.save(categoryEntity);
    }

    public void deleteCategory(long id) {

        CategoryEntity categoryEntity = findCategoryEntity(id);

        validateReferentialIntegrity(categoryEntity);

        repo.delete(categoryEntity);
    }

    private void validateReferentialIntegrity(CategoryEntity categoryEntity) {

        IllegalDeletionException e = null;

        for (CategoryEntity childCategoryEntity : categoryEntity.getChildCategories()) {
            if (e == null) {
                e = new IllegalDeletionException();
            }

            e.addDependentObject("category", childCategoryEntity.getId(), childCategoryEntity.getName());
        }

        for (SuitEntity suit : categoryEntity.getSuits()) {
            if (e == null) {
                e = new IllegalDeletionException();
            }

            e.addDependentObject("suit", suit.getId(), suit.getName());
        }

        if (e != null) {
            throw e;
        }
    }

    private CategoryEntity findCategoryEntity(Long id) {

        CategoryEntity categoryEntity = repo.findOne(id);

        if (categoryEntity == null) {
            throw new EntityNotFoundException(CategoryEntity.class, id);
        }

        return categoryEntity;
    }
}
