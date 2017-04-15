package ru.andrewquiz.service.quiz;

import org.springframework.stereotype.Component;
import ru.andrewquiz.dao.quiz.CategoryEntity;
import ru.andrewquiz.dao.quiz.SuitEntity;
import ru.andrewquiz.dto.quiz.Category;
import ru.andrewquiz.rest.exception.IllegalDeletionException;
import ru.andrewquiz.service.Validator;

/**
 * Created by Andrew on 15.04.2017.
 */

@Component
public class CategoryValidator implements Validator<Category, CategoryEntity> {

    @Override
    public void validateReferentialIntegrity(CategoryEntity categoryEntity) {

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
}
