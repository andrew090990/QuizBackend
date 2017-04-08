package ru.andrewquiz.service.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.andrewquiz.dao.quiz.SuitEntity;
import ru.andrewquiz.dto.quiz.Suit;
import ru.andrewquiz.repository.quiz.SuitRepository;
import ru.andrewquiz.util.mapper.CustomDozerBeanMapper;

import java.util.List;

/**
 * Created by Andrew on 02.04.2017.
 */

@Service
public class SuitService {

    public List<Suit> getSuits() {

        Iterable<SuitEntity> suitEntities = repo.findAll();

        List<Suit> suits = mapper.mapList(suitEntities, Suit.class);

        return suits;
    }

    public List<Suit> getSuitsByCategoryId(long categoryId) {

        Iterable<SuitEntity> suitEntities = repo.findByCategoryId(categoryId);

        List<Suit> suits = mapper.mapList(suitEntities, Suit.class);

        return suits;
    }

    public Suit getSuit(long id) {

        SuitEntity suitEntity = repo.findOne(id);

        Suit suit = mapper.map(suitEntity, Suit.class);

        return suit;
    }


    @Autowired
    private SuitRepository repo;

    @Autowired
    private CustomDozerBeanMapper mapper;
}
