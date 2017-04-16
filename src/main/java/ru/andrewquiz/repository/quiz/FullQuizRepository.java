package ru.andrewquiz.repository.quiz;

import org.springframework.data.repository.CrudRepository;
import ru.andrewquiz.dao.quiz.FullQuizEntity;

import java.util.List;

/**
 * Created by Andrew on 16.04.2017.
 */

public interface FullQuizRepository extends CrudRepository<FullQuizEntity, Long>
{
    public List<FullQuizEntity> findBySuitId(long suitId);
}
