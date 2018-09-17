package ru.andrewquiz.dao.quiz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Andrew on 29.04.2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/appContext.xml"})
public class QuestionEntityTest {

    @Autowired
    private QuestionEntity questionEntity1;

    @Autowired
    private FullQuizEntity fullQuizEntity1;

    @Test
    public void testEqualsHashCode() {

        assertNotEquals("These should not be equal", questionEntity1, null);
        assertNotEquals("These should not be equal", questionEntity1, new Object());

        QuestionEntity questionEntity2 = new QuestionEntity();
        assertNotEquals("These should not be equal", questionEntity1, questionEntity2);

        questionEntity2.setFullQuiz(questionEntity1.getFullQuiz());
        assertNotEquals("These should not be equal", questionEntity1, questionEntity2);

        questionEntity2.setFullQuiz(null);
        questionEntity2.setQuestionNumber(questionEntity1.getQuestionNumber());
        assertNotEquals("These should not be equal", questionEntity1, questionEntity2);

        questionEntity2.setFullQuiz(questionEntity1.getFullQuiz());
        assertEquals("These should be equal", questionEntity1, questionEntity2);
        assertEquals("Hash codes should be equal", questionEntity1.hashCode(), questionEntity2.hashCode());
    }

    @Test
    public void testSetFullQuizWhenSetSomeFullQuiz() {

        FullQuizEntity fullQuizEntity2 = new FullQuizEntity();

        questionEntity1.setFullQuiz(null);
        questionEntity1.setFullQuiz(fullQuizEntity2);

        assertTrue("FullQuiz should be set", questionEntity1.getFullQuiz() == fullQuizEntity2);
        assertTrue("FullQuiz should contain question under test", fullQuizEntity2.getQuestions().contains(questionEntity1));
    }

    @Test
    public void testSetFullQuizWhenSetNull() {

        FullQuizEntity fullQuizEntity2 = new FullQuizEntity();

        questionEntity1.setFullQuiz(fullQuizEntity2);
        questionEntity1.setFullQuiz(null);

        assertTrue("FullQuiz should be set to null", questionEntity1.getFullQuiz() == null);
        assertFalse("FullQuiz should not contain question under test", fullQuizEntity2.getQuestions().contains(questionEntity1));
    }

    @Test
    public void testSetFullQuizWhenChangedFullQuiz() {

        FullQuizEntity fullQuizEntity2 = new FullQuizEntity();
        questionEntity1.setFullQuiz(fullQuizEntity2);
        FullQuizEntity fullQuizEntity3 = new FullQuizEntity();
        questionEntity1.setFullQuiz(fullQuizEntity3);

        assertTrue("FullQuiz should be set to new", questionEntity1.getFullQuiz() == fullQuizEntity3);
        assertFalse("Old fullQuiz should not contain question under test", fullQuizEntity2.getQuestions().contains(questionEntity1));
        assertTrue("New fullQuiz should contain question under test", fullQuizEntity3.getQuestions().contains(questionEntity1));
    }

    @Test
    public void testSetFullQuizWhenChangedAnswerToEqual() {
        questionEntity1.setFullQuiz(fullQuizEntity1);

        QuestionEntity questionEntity3 = new QuestionEntity();
        questionEntity3.setQuestionNumber(questionEntity1.getQuestionNumber());
        questionEntity3.setFullQuiz(questionEntity1.getFullQuiz());

        List<QuestionEntity> questions = fullQuizEntity1.getQuestions();

        assertTrue("Question should have been updated",
                questions.get(questions.indexOf(questionEntity1)) == questionEntity3);
    }
}
