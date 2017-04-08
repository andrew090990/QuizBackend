package ru.andrewquiz.repository.quiz;

import org.springframework.data.repository.CrudRepository;
import ru.andrewquiz.dao.quiz.SuitEntity;

import java.util.List;

/**
 * Created by Andrew on 29.03.2017.
 */

public interface SuitRepository extends CrudRepository<SuitEntity, Long>
{
    public List<SuitEntity> findByCategoryId(long categoryId);
}
