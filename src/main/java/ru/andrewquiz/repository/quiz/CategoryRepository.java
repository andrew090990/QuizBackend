package ru.andrewquiz.repository.quiz;

import org.springframework.data.repository.CrudRepository;
import ru.andrewquiz.dao.quiz.CategoryEntity;

/**
 * Created by Andrew on 29.03.2017.
 */

public interface CategoryRepository extends CrudRepository<CategoryEntity, Long>
{
}
