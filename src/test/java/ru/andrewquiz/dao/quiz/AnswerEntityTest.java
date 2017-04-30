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
public class AnswerEntityTest {

    @Autowired
    private AnswerEntity answerEntity1;

    @Autowired
    private FullQuizEntity fullQuizEntity1;

    @Test
    public void testEqualsHashCode() {

        assertNotEquals("These should not be equal", answerEntity1, null);
        assertNotEquals("These should not be equal", answerEntity1, new Object());

        AnswerEntity answerEntity2 = new AnswerEntity();
        assertNotEquals("These should not be equal", answerEntity1, answerEntity2);

        answerEntity2.setFullQuiz(answerEntity1.getFullQuiz());
        assertNotEquals("These should not be equal", answerEntity1, answerEntity2);

        answerEntity2.setFullQuiz(null);
        answerEntity2.setAnswerNumber(answerEntity1.getAnswerNumber());
        assertNotEquals("These should not be equal", answerEntity1, answerEntity2);

        answerEntity2.setFullQuiz(answerEntity1.getFullQuiz());
        assertEquals("These should be equal", answerEntity1, answerEntity2);
        assertEquals("Hash codes should be equal", answerEntity1.hashCode(), answerEntity2.hashCode());
    }

    @Test
    public void testSetFullQuizWhenSetSomeFullQuiz() {

        FullQuizEntity fullQuizEntity2 = new FullQuizEntity();

        answerEntity1.setFullQuiz(null);
        answerEntity1.setFullQuiz(fullQuizEntity2);

        assertTrue("FullQuiz should be set", answerEntity1.getFullQuiz() == fullQuizEntity2);
        assertTrue("FullQuiz should contain answer under test", fullQuizEntity2.getAnswers().contains(answerEntity1));
    }

    @Test
    public void testSetFullQuizWhenSetNull() {

        FullQuizEntity fullQuizEntity2 = new FullQuizEntity();

        answerEntity1.setFullQuiz(fullQuizEntity2);
        answerEntity1.setFullQuiz(null);

        assertTrue("FullQuiz should be set to null", answerEntity1.getFullQuiz() == null);
        assertFalse("FullQuiz should not contain answer under test", fullQuizEntity2.getAnswers().contains(answerEntity1));
    }

    @Test
    public void testSetFullQuizWhenChangedFullQuiz() {

        FullQuizEntity fullQuizEntity2 = new FullQuizEntity();
        answerEntity1.setFullQuiz(fullQuizEntity2);
        FullQuizEntity fullQuizEntity3 = new FullQuizEntity();
        answerEntity1.setFullQuiz(fullQuizEntity3);

        assertTrue("FullQuiz should be set to new", answerEntity1.getFullQuiz() == fullQuizEntity3);
        assertFalse("Old fullQuiz should not contain answer under test", fullQuizEntity2.getAnswers().contains(answerEntity1));
        assertTrue("New fullQuiz should contain answer under test", fullQuizEntity3.getAnswers().contains(answerEntity1));
    }

    @Test
    public void testSetFullQuizWhenChangedAnswerToEqual() {
        answerEntity1.setFullQuiz(fullQuizEntity1);

        AnswerEntity answerEntity3 = new AnswerEntity();
        answerEntity3.setAnswerNumber(answerEntity1.getAnswerNumber());
        answerEntity3.setFullQuiz(answerEntity1.getFullQuiz());

        List<AnswerEntity> answers = fullQuizEntity1.getAnswers();

        assertTrue("Answer should have been updated",
                answers.get(answers.indexOf(answerEntity1)) == answerEntity3);
    }
}
