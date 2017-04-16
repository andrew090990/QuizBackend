package ru.andrewquiz.service.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import ru.andrewquiz.dao.quiz.QuizEntity;
import ru.andrewquiz.dto.quiz.Quiz;
import ru.andrewquiz.mapper.CustomDozerBeanMapper;
import ru.andrewquiz.repository.quiz.QuizRepository;
import ru.andrewquiz.service.AbstractResourceService;
import ru.andrewquiz.service.Validator;

import java.util.List;

/**
 * Created by Andrew on 16.04.2017.
 */

@Service
public class QuizService extends AbstractResourceService<Quiz, QuizEntity, Long> {

    private QuizValidator validator;

    private CustomDozerBeanMapper mapper;

    private QuizRepository repo;

    @Autowired
    public QuizService(QuizRepository repo, CustomDozerBeanMapper mapper, QuizValidator validator) {

        this.repo = repo;
        this.mapper = mapper;
        this.validator = validator;
    }

    public List<Quiz> getQuizesBySuitId(Long suitId) {

        Iterable<QuizEntity> quizEntities = repo.findBySuitId(suitId);

        List<Quiz> quizes = mapper.mapList(quizEntities, Quiz.class);

        return quizes;
    }

    @Override
    protected Class<Quiz> getDtoClass() {
        return Quiz.class;
    }

    @Override
    protected Class<QuizEntity> getEntityClass() {
        return QuizEntity.class;
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
    protected CustomDozerBeanMapper getMapper() {
        return mapper;
    }
}
