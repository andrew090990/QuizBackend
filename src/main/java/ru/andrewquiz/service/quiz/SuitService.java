package ru.andrewquiz.service.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import ru.andrewquiz.dao.quiz.SuitEntity;
import ru.andrewquiz.dto.quiz.Suit;
import ru.andrewquiz.repository.quiz.SuitRepository;
import ru.andrewquiz.service.AbstractResourceService;
import ru.andrewquiz.service.Validator;
import ru.andrewquiz.service.mapper.AbstractMapper;
import ru.andrewquiz.service.mapper.quiz.SuitDtoToEntityMapper;
import ru.andrewquiz.service.mapper.quiz.SuitEntityToDtoMapper;

import java.util.List;

/**
 * Created by Andrew on 02.04.2017.
 */

@Service
public class SuitService extends AbstractResourceService<Suit, SuitEntity> {

    private SuitRepository repo;

    private SuitValidator validator;

    private SuitDtoToEntityMapper dtoToEntityMapper;

    private SuitEntityToDtoMapper entityToDtoMapper;

    @Autowired
    public SuitService(SuitRepository repo, SuitValidator validator, SuitDtoToEntityMapper dtoToEntityMapper, SuitEntityToDtoMapper entityToDtoMapper) {

        this.repo = repo;
        this.validator = validator;
        this.entityToDtoMapper = entityToDtoMapper;
        this.dtoToEntityMapper = dtoToEntityMapper;
    }

    public List<Suit> getSuitsByCategoryId(Long categoryId) {

        Iterable<SuitEntity> suitEntities = repo.findByCategoryId(categoryId);

        List<Suit> suits = entityToDtoMapper.mapList(suitEntities);

        return suits;
    }

    @Override
    protected Class<Suit> getDtoClass() {
        return Suit.class;
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
    protected AbstractMapper<Suit, SuitEntity> getDtoToEntityMapper() {
        return dtoToEntityMapper;
    }

    @Override
    protected AbstractMapper<SuitEntity, Suit> getEntityToDtoMapper() {
        return entityToDtoMapper;
    }

}
