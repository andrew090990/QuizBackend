package ru.andrewquiz.service.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import ru.andrewquiz.dao.quiz.SuitEntity;
import ru.andrewquiz.dto.quiz.Suit;
import ru.andrewquiz.mapper.CustomDozerBeanMapper;
import ru.andrewquiz.repository.quiz.SuitRepository;
import ru.andrewquiz.service.AbstractResourceService;
import ru.andrewquiz.service.Validator;

import java.util.List;

/**
 * Created by Andrew on 02.04.2017.
 */

@Service
public class SuitService extends AbstractResourceService<Suit, SuitEntity, Long> {

    private SuitRepository repo;

    private CustomDozerBeanMapper mapper;

    private SuitValidator validator;

    @Autowired
    public SuitService(SuitRepository repo, CustomDozerBeanMapper mapper, SuitValidator validator) {

        this.repo = repo;
        this.mapper = mapper;
        this.validator = validator;
    }

    public List<Suit> getSuitsByCategoryId(Long categoryId) {

        Iterable<SuitEntity> suitEntities = repo.findByCategoryId(categoryId);

        List<Suit> suits = mapper.mapList(suitEntities, Suit.class);

        return suits;
    }

    @Override
    protected Class<Suit> getDtoClass() {
        return Suit.class;
    }

    @Override
    protected Class<SuitEntity> getEntityClass() {
        return SuitEntity.class;
    }

    @Override
    protected CrudRepository<SuitEntity, Long> getRepo() {
        return repo;
    }

    @Override
    protected Validator<Suit, SuitEntity> getValidator() {
        return validator;
    }

    @Override
    protected CustomDozerBeanMapper getMapper() {
        return mapper;
    }

}
