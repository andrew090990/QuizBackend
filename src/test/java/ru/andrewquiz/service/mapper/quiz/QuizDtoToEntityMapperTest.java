package ru.andrewquiz.service.mapper.quiz;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.andrewquiz.dao.quiz.QuizEntity;
import ru.andrewquiz.dao.quiz.SuitEntity;
import ru.andrewquiz.dto.quiz.Quiz;
import ru.andrewquiz.repository.quiz.SuitRepository;
import ru.andrewquiz.rest.exception.EntityNotFoundException;
import ru.andrewquiz.service.mapper.MappingException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Created by Andrew on 01.05.2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/appContext.xml"})
public class QuizDtoToEntityMapperTest {

    @Autowired
    @InjectMocks
    private QuizDtoToEntityMapper mapper;

    @Mock
    private SuitRepository suitRepo;

    @Autowired
    private SuitEntity suitEntity1;

    @Autowired
    private Quiz quiz1;

    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMapWhenMapSomeQuiz() {

        quiz1.setSuitId(1L);

        when(suitRepo.findOne(quiz1.getSuitId())).thenReturn(suitEntity1);

        QuizEntity result = mapper.map(quiz1);

        assertTrue("Suit should have been set", result.getSuit() == suitEntity1);
        assertEquals("Name should have been set", quiz1.getName(), result.getName());
        assertTrue("Id shouldn't have been set", result.getId() == null);
    }

    @Test(expected = MappingException.class)
    public void testMapWhenMapNull() {

        QuizEntity result = mapper.map(null);
    }

    @Test(expected = EntityNotFoundException.class)
    public void testMapWhenCategoryNotFound() {

        quiz1.setSuitId(1L);

        when(suitRepo.findOne(quiz1.getSuitId())).thenReturn(null);

        QuizEntity result = mapper.map(quiz1);
    }

}
