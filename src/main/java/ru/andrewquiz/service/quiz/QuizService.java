package ru.andrewquiz.service.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import ru.andrewquiz.dao.quiz.QuizEntity;
import ru.andrewquiz.dto.quiz.Quiz;
import ru.andrewquiz.repository.quiz.QuizRepository;
import ru.andrewquiz.service.AbstractResourceService;
import ru.andrewquiz.service.Validator;
import ru.andrewquiz.service.mapper.AbstractMapper;
import ru.andrewquiz.service.mapper.quiz.QuizDtoToEntityMapper;
import ru.andrewquiz.service.mapper.quiz.QuizEntityToDtoMapper;

import java.util.List;

/**
 * Created by Andrew on 16.04.2017.
 */

@Service
public class QuizService extends AbstractResourceService<Quiz, QuizEntity> {

    private QuizValidator validator;

    private QuizRepository repo;

    private QuizDtoToEntityMapper dtoToEntityMapper;

    private QuizEntityToDtoMapper entityToDtoMapper;

    @Autowired
    public QuizService(QuizRepository repo, QuizValidator validator,
                           QuizDtoToEntityMapper dtoToEntityMapper, QuizEntityToDtoMapper entityToDtoMapper) {

        this.repo = repo;
        this.validator = validator;
        this.entityToDtoMapper = entityToDtoMapper;
        this.dtoToEntityMapper = dtoToEntityMapper;
    }

    public List<Quiz> getQuizesBySuitId(Long suitId) {

        Iterable<QuizEntity> quizEntities = repo.findBySuitId(suitId);

        List<Quiz> quizes = entityToDtoMapper.mapList(quizEntities);

        return quizes;
    }

    @Override
    protected Class<Quiz> getDtoClass() {
        return Quiz.class;
    }

    @Override
    protected CrudRepository<QuizEntity, Long> getRepo() {
        return repo;
    }

    @Override
    protected Validator<Quiz, QuizEntity> getValidator() {
        return validator;
    }

    @Override
    protected AbstractMapper<Quiz, QuizEntity> getDtoToEntityMapper() {
        return dtoToEntityMapper;
    }

    @Override
    protected AbstractMapper<QuizEntity, Quiz> getEntityToDtoMapper() {
        return entityToDtoMapper;
    }
}
