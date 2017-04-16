package ru.andrewquiz.service.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import ru.andrewquiz.dao.quiz.FullQuizEntity;
import ru.andrewquiz.dto.quiz.FullQuiz;
import ru.andrewquiz.mapper.CustomDozerBeanMapper;
import ru.andrewquiz.repository.quiz.FullQuizRepository;
import ru.andrewquiz.service.AbstractResourceService;
import ru.andrewquiz.service.Validator;

import java.util.List;

/**
 * Created by Andrew on 16.04.2017.
 */

@Service
public class FullQuizService extends AbstractResourceService<FullQuiz, FullQuizEntity, Long> {

    private FullQuizValidator validator;

    private CustomDozerBeanMapper mapper;

    private FullQuizRepository repo;

    @Autowired
    public FullQuizService(FullQuizRepository repo, CustomDozerBeanMapper mapper, FullQuizValidator validator) {

        this.repo = repo;
        this.mapper = mapper;
        this.validator = validator;
    }

    public List<FullQuiz> getFullQuizesBySuitId(Long suitId) {

        Iterable<FullQuizEntity> fullQuizEntities = repo.findBySuitId(suitId);

        List<FullQuiz> fullQuizes = mapper.mapList(fullQuizEntities, FullQuiz.class);

        return fullQuizes;
    }

    @Override
    protected Class<FullQuiz> getDtoClass() {
        return FullQuiz.class;
    }

    @Override
    protected Class<FullQuizEntity> getEntityClass() {
        return FullQuizEntity.class;
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
    protected CustomDozerBeanMapper getMapper() {
        return mapper;
    }
}
