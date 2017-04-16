package ru.andrewquiz.repository.quiz;

import org.springframework.data.repository.CrudRepository;
import ru.andrewquiz.dao.quiz.QuizEntity;

import java.util.List;

/**
 * Created by Andrew on 16.04.2017.
 */

public interface QuizRepository extends CrudRepository<QuizEntity, Long>
{
    public List<QuizEntity> findBySuitId(long suitId);
}
