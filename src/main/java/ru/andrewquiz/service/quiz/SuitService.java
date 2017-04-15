package ru.andrewquiz.service.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.andrewquiz.dao.quiz.SuitEntity;
import ru.andrewquiz.dto.quiz.Suit;
import ru.andrewquiz.mapper.CustomDozerBeanMapper;
import ru.andrewquiz.repository.quiz.SuitRepository;
import ru.andrewquiz.rest.exception.EntityNotFoundException;
import ru.andrewquiz.rest.exception.IllegalRequestException;

import java.util.Calendar;
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

        SuitEntity suitEntity = findSuitEntity(id);

        Suit suit = mapper.map(suitEntity, Suit.class);

        return suit;
    }

    public Long createSuit(Suit suit) {

        //TODO validation

        if (suit.getId() != null) {
            throw new IllegalRequestException("Id must be null when posting new resource.");
        }

        SuitEntity suitEntity = mapper.map(suit, SuitEntity.class);

        suitEntity.setCreatedAt(Calendar.getInstance());

        repo.save(suitEntity);

        return suitEntity.getId();
    }

    public void updateSuit(Suit suit, Long id) {

        //TODO validation

        if (!repo.exists(suit.getId())) {
            throw new EntityNotFoundException(SuitEntity.class,id);
        }

        SuitEntity suitEntity = mapper.map(suit, SuitEntity.class);

        suitEntity.setId(id);

        suitEntity.setUpdatedAt(Calendar.getInstance());

        repo.save(suitEntity);
    }

    public void deleteSuit(long id) {

        SuitEntity suitEntity = findSuitEntity(id);

        validateReferentialIntegrity(suitEntity);

        repo.delete(suitEntity);
    }

    private void validateReferentialIntegrity(SuitEntity suitEntity) {
        return;
    }


    private SuitEntity findSuitEntity (Long id) {

        SuitEntity suitEntity = repo.findOne(id);

        if (suitEntity == null) {
            throw new EntityNotFoundException(SuitEntity.class, id);
        }

        return suitEntity;
    }

}
