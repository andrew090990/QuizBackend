package ru.andrewquiz.service.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.andrewquiz.dao.quiz.CategoryEntity;
import ru.andrewquiz.dto.quiz.Category;
import ru.andrewquiz.repository.quiz.CategoryRepository;
import ru.andrewquiz.rest.exception.EntityNotFoundException;
import ru.andrewquiz.util.mapper.CustomDozerBeanMapper;

import java.util.List;

/**
 * Created by Andrew on 02.04.2017.
 */

@Service
public class CategoryService {

    public List<Category> getCategories() {

        Iterable<CategoryEntity> categoryEntities = repo.findAll();

        List<Category> categories = mapper.mapList(categoryEntities, Category.class);

        return categories;
    }

    public Category getCategory(long id) {

        CategoryEntity categoryEntity = repo.findOne(id);

        if (categoryEntity == null) {
            throw new EntityNotFoundException("Cannot find category with id " + id);
        }

        Category category = mapper.map(categoryEntity, Category.class);

        return category;
    }


    @Autowired
    private CategoryRepository repo;

    @Autowired
    private CustomDozerBeanMapper mapper;
}
