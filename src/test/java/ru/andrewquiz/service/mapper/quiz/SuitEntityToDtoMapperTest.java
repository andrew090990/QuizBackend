package ru.andrewquiz.service.mapper.quiz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.andrewquiz.dao.quiz.CategoryEntity;
import ru.andrewquiz.dao.quiz.SuitEntity;
import ru.andrewquiz.dto.quiz.Suit;
import ru.andrewquiz.service.mapper.MappingException;

import static org.junit.Assert.assertEquals;

/**
 * Created by Andrew on 01.05.2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/appContext.xml"})
public class SuitEntityToDtoMapperTest {

    @Autowired
    private SuitEntityToDtoMapper mapper;

    @Autowired
    private CategoryEntity categoryEntity2;

    @Autowired
    private SuitEntity suitEntity1;

    @Test
    public void testMapWhenMapSomeSuit() {

        suitEntity1.setCategory(categoryEntity2);

        Suit result = mapper.map(suitEntity1);

        assertEquals("Category id should have been set", suitEntity1.getCategory().getId(), result.getCategoryId());
        assertEquals("Name should have been set", suitEntity1.getName(), result.getName());
        assertEquals("Id should have been set", suitEntity1.getId(), result.getId());
    }

    @Test(expected = MappingException.class)
    public void testMapWhenMapNull() {

        Suit result = mapper.map(null);
    }

}
