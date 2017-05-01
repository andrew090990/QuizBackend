package ru.andrewquiz.service.mapper.quiz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.andrewquiz.dao.quiz.QuizEntity;
import ru.andrewquiz.dao.quiz.SuitEntity;
import ru.andrewquiz.dto.quiz.Quiz;
import ru.andrewquiz.service.mapper.MappingException;

import static org.junit.Assert.assertEquals;

/**
 * Created by Andrew on 01.05.2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/appContext.xml"})
public class QuizEntityToDtoMapperTest {

    @Autowired
    private QuizEntityToDtoMapper mapper;

    @Autowired
    private SuitEntity suitEntity1;

    @Autowired
    private QuizEntity quizEntity1;

    @Test
    public void testMapWhenMapSomeQuiz() {

        quizEntity1.setSuit(suitEntity1);

        Quiz result = mapper.map(quizEntity1);

        assertEquals("Suit id should have been set", quizEntity1.getSuit().getId(), result.getSuitId());
        assertEquals("Name should have been set", quizEntity1.getName(), result.getName());
        assertEquals("Id should have been set", quizEntity1.getId(), result.getId());
    }

    @Test(expected = MappingException.class)
    public void testMapWhenMapNull() {

        Quiz result = mapper.map(null);
    }

}
