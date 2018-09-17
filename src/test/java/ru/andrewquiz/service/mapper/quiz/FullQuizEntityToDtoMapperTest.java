package ru.andrewquiz.service.mapper.quiz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.andrewquiz.dao.quiz.AnswerEntity;
import ru.andrewquiz.dao.quiz.FullQuizEntity;
import ru.andrewquiz.dao.quiz.QuestionEntity;
import ru.andrewquiz.dao.quiz.SuitEntity;
import ru.andrewquiz.dto.quiz.FullQuiz;
import ru.andrewquiz.dto.quiz.Question;
import ru.andrewquiz.dto.quiz.Quiz;
import ru.andrewquiz.service.mapper.MappingException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Andrew on 01.05.2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/appContext.xml"})
public class FullQuizEntityToDtoMapperTest {

    @Autowired
    private FullQuizEntityToDtoMapper mapper;

    @Autowired
    private SuitEntity suitEntity1;

    @Autowired
    private FullQuizEntity fullQuizEntity1;

    @Autowired
    private AnswerEntity answerEntity1;

    @Autowired
    private QuestionEntity questionEntity1;

    @Test
    public void testMapWhenMapSomeQuiz() {

        questionEntity1.getAnswers().clear();
        questionEntity1.getAnswers().add(answerEntity1);
        questionEntity1.getKeys().clear();
        questionEntity1.getKeys().add(answerEntity1);

        fullQuizEntity1.setSuit(suitEntity1);
        fullQuizEntity1.getAnswers().clear();
        fullQuizEntity1.getAnswers().add(answerEntity1);
        fullQuizEntity1.getQuestions().clear();
        fullQuizEntity1.getQuestions().add(questionEntity1);

        FullQuiz result = mapper.map(fullQuizEntity1);

        assertEquals("Content should have been set", fullQuizEntity1.getContent(), result.getContent());

        assertTrue("Answer should have been set", result.getAnswers().size() == 1
                && result.getAnswers().get(0).getAnswerNumber() == answerEntity1.getAnswerNumber());
        assertTrue("Question should have been set", result.getQuestions().size() == 1
                && result.getQuestions().get(0).getQuestionNumber() == questionEntity1.getQuestionNumber());

        Question resultQuestion = result.getQuestions().get(0);
        assertTrue("Answer option should have been set", resultQuestion.getAnswers().size() == 1
                && resultQuestion.getAnswers().get(0).equals(answerEntity1.getAnswerNumber()));
        assertTrue("Key should have been set", resultQuestion.getKeys().size() == 1
                && resultQuestion.getKeys().get(0).equals(answerEntity1.getAnswerNumber()));

        assertEquals("Id should have been set", fullQuizEntity1.getId(), result.getId());
    }

    @Test(expected = MappingException.class)
    public void testMapWhenMapNull() {

        Quiz result = mapper.map(null);
    }

}
