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
import ru.andrewquiz.dao.quiz.FullQuizEntity;
import ru.andrewquiz.dao.quiz.QuestionEntity;
import ru.andrewquiz.dao.quiz.QuizEntity;
import ru.andrewquiz.dto.quiz.Answer;
import ru.andrewquiz.dto.quiz.FullQuiz;
import ru.andrewquiz.dto.quiz.Question;
import ru.andrewquiz.service.mapper.MappingException;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;

/**
 * Created by Andrew on 01.05.2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/appContext.xml"})
public class FullQuizDtoToEntityMapperTest {

    @Autowired
    @InjectMocks
    private FullQuizDtoToEntityMapper mapper;

    @Mock
    private QuizDtoToEntityMapper baseMapper;

    @Autowired
    private QuizEntity quizEntity1;

    @Autowired
    private FullQuiz fullQuiz1;

    @Autowired
    private Answer answer1;

    @Autowired
    private Question question1;

    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMapWhenMapSomeFullQuiz() {
        quizEntity1.setId(null);

        question1.getAnswers().clear();
        question1.getAnswers().add(answer1.getAnswerNumber());
        question1.getKeys().clear();
        question1.getKeys().add(answer1.getAnswerNumber());

        fullQuiz1.setSuitId(1L);
        fullQuiz1.getAnswers().clear();
        fullQuiz1.getAnswers().add(answer1);
        fullQuiz1.getQuestions().clear();
        fullQuiz1.getQuestions().add(question1);

        when(baseMapper.performMapping(anyObject())).thenReturn(quizEntity1);

        FullQuizEntity result = mapper.map(fullQuiz1);

        assertTrue("Content should have been set", result.getContent() == fullQuiz1.getContent());

        assertTrue("Answer should have been set", result.getAnswers().size() == 1
                && result.getAnswers().get(0).getAnswerNumber() == answer1.getAnswerNumber()
                && result.getAnswers().get(0).getFullQuiz() == result);
        assertTrue("Question should have been set", result.getQuestions().size() == 1
                && result.getQuestions().get(0).getQuestionNumber() == question1.getQuestionNumber()
                && result.getQuestions().get(0).getFullQuiz() == result);

        QuestionEntity resultQuestion = result.getQuestions().get(0);
        assertTrue("Answer option should have been set", resultQuestion.getAnswers().size() == 1
                && resultQuestion.getAnswers().get(0) == result.getAnswers().get(0));
        assertTrue("Key should have been set", resultQuestion.getKeys().size() == 1
                && resultQuestion.getKeys().get(0) == result.getAnswers().get(0));

        assertTrue("Id shouldn't have been set", result.getId() == null);
    }

    @Test(expected = MappingException.class)
    public void testMapWhenBadAnswerOptions() {

        quizEntity1.setId(null);

        question1.getAnswers().clear();
        question1.getAnswers().add(answer1.getAnswerNumber() + 1); //bad reference
        question1.getKeys().clear();
        question1.getKeys().add(answer1.getAnswerNumber());

        fullQuiz1.setSuitId(1L);
        fullQuiz1.getAnswers().clear();
        fullQuiz1.getAnswers().add(answer1);
        fullQuiz1.getQuestions().clear();
        fullQuiz1.getQuestions().add(question1);

        when(baseMapper.performMapping(anyObject())).thenReturn(quizEntity1);

        FullQuizEntity result = mapper.map(fullQuiz1);
    }

    @Test(expected = MappingException.class)
    public void testMapWhenBadKeys() {

        quizEntity1.setId(null);

        question1.getAnswers().clear();
        question1.getAnswers().add(answer1.getAnswerNumber());
        question1.getKeys().clear();
        question1.getKeys().add(answer1.getAnswerNumber() + 1); //bad reference

        fullQuiz1.setSuitId(1L);
        fullQuiz1.getAnswers().clear();
        fullQuiz1.getAnswers().add(answer1);
        fullQuiz1.getQuestions().clear();
        fullQuiz1.getQuestions().add(question1);

        when(baseMapper.performMapping(anyObject())).thenReturn(quizEntity1);

        FullQuizEntity result = mapper.map(fullQuiz1);
    }

    @Test(expected = MappingException.class)
    public void testMapWhenMapNull() {

        FullQuizEntity result = mapper.map(null);
    }

}
