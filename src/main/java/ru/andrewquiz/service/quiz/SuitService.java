package ru.andrewquiz.service.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.andrewquiz.dao.quiz.SuitEntity;
import ru.andrewquiz.dto.quiz.Suit;
import ru.andrewquiz.mapper.CustomDozerBeanMapper;
import ru.andrewquiz.repository.quiz.SuitRepository;
import ru.andrewquiz.rest.exception.EntityNotFoundException;

import java.util.List;

/**
 * Created by Andrew on 02.04.2017.
 */

@Service
public class SuitService {

    private SuitRepository repo;

    private CustomDozerBeanMapper mapper;

    @Autowired
    public SuitService(SuitRepository repo, CustomDozerBeanMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public List<Suit> getSuits() {

        Iterable<SuitEntity> suitEntities = repo.findAll();

        List<Suit> suits = mapper.mapList(suitEntities, Suit.class);

        return suits;
    }

    public List<Suit> getSuitsByCategoryId(Long categoryId) {

        Iterable<SuitEntity> suitEntities = repo.findByCategoryId(categoryId);

        List<Suit> suits = mapper.mapList(suitEntities, Suit.class);

        return suits;
    }

    public Suit getSuit(Long id) {

        SuitEntity suitEntity = repo.findOne(id);

        if (suitEntity == null) {
            throw new EntityNotFoundException(SuitEntity.class, id);
        }

        Suit suit = mapper.map(suitEntity, Suit.class);

        return suit;
    }

}
