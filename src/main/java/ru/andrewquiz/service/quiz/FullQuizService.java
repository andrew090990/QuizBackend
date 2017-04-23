package ru.andrewquiz.service.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import ru.andrewquiz.dao.quiz.FullQuizEntity;
import ru.andrewquiz.dto.quiz.FullQuiz;
import ru.andrewquiz.repository.quiz.FullQuizRepository;
import ru.andrewquiz.service.AbstractResourceService;
import ru.andrewquiz.service.Validator;
import ru.andrewquiz.service.mapper.AbstractMapper;
import ru.andrewquiz.service.mapper.quiz.FullQuizDtoToEntityMapper;
import ru.andrewquiz.service.mapper.quiz.FullQuizEntityToDtoMapper;

import java.util.List;

/**
 * Created by Andrew on 16.04.2017.
 */

@Service
public class FullQuizService extends AbstractResourceService<FullQuiz, FullQuizEntity> {

    private FullQuizValidator validator;

    private FullQuizRepository repo;

    private FullQuizDtoToEntityMapper dtoToEntityMapper;

    private FullQuizEntityToDtoMapper entityToDtoMapper;

    @Autowired
    public FullQuizService(FullQuizRepository repo, FullQuizValidator validator,
                       FullQuizDtoToEntityMapper dtoToEntityMapper, FullQuizEntityToDtoMapper entityToDtoMapper) {

        this.repo = repo;
        this.validator = validator;
        this.entityToDtoMapper = entityToDtoMapper;
        this.dtoToEntityMapper = dtoToEntityMapper;
    }

    public List<FullQuiz> getFullQuizesBySuitId(Long suitId) {

        Iterable<FullQuizEntity> fullQuizEntities = repo.findBySuitId(suitId);

        List<FullQuiz> fullQuizes = entityToDtoMapper.mapList(fullQuizEntities);

        return fullQuizes;
    }

    @Override
    protected Class<FullQuiz> getDtoClass() {
        return FullQuiz.class;
    }

    @Override
    protected CrudRepository<FullQuizEntity, Long> getRepo() {
        return repo;
    }

    @Override
    protected Validator<FullQuiz, FullQuizEntity> getValidator() {
        return validator;
    }

    @Override
    protected AbstractMapper<FullQuiz, FullQuizEntity> getDtoToEntityMapper() {
        return dtoToEntityMapper;
    }

    @Override
    protected AbstractMapper<FullQuizEntity, FullQuiz> getEntityToDtoMapper() {
        return entityToDtoMapper;
    }
}
