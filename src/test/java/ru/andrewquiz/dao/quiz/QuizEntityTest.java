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
public class QuizEntityTest {

    @Autowired
    private QuizEntity quizEntity1;

    @Autowired
    private FullQuizEntity fullQuizEntity1;

    @Autowired
    private SuitEntity suitEntity1;

    @Test
    public void testEqualsHashCode() {

        assertNotEquals("These should not be equal", quizEntity1, null);
        assertNotEquals("These should not be equal", quizEntity1, new Object());

        QuizEntity quizEntity2 = new QuizEntity();
        assertNotEquals("These should not be equal", quizEntity1, quizEntity2);

        assertNotEquals("These should not be equal", quizEntity1, fullQuizEntity1);

        quizEntity2.setId(quizEntity1.getId());
        assertEquals("These should be equal", quizEntity1, quizEntity2);
        assertEquals("Hash codes should be equal", quizEntity1.hashCode(), quizEntity2.hashCode());
    }

    @Test
    public void testSetSuitWhenSetSomeSuit() {

        QuizEntity quizEntity3 = new QuizEntity();
        quizEntity3.setSuit(suitEntity1);

        assertTrue("Suit should be set", quizEntity3.getSuit() == suitEntity1);
        assertTrue("Suit should contain quiz under test", suitEntity1.getQuizes().contains(quizEntity3));
    }

    @Test
    public void testSetSuitWhenSetNull() {

        quizEntity1.setSuit(suitEntity1);
        quizEntity1.setSuit(null);

        assertTrue("Suit should be set to null", quizEntity1.getSuit() == null);
        assertFalse("Suit should not contain quiz under test", suitEntity1.getQuizes().contains(quizEntity1));
    }

    @Test
    public void testSetSuitWhenChangedSuit() {

        quizEntity1.setSuit(suitEntity1);
        SuitEntity suitEntity3 = new SuitEntity();
        quizEntity1.setSuit(suitEntity3);

        assertTrue("Suit should be set to new", quizEntity1.getSuit() == suitEntity3);
        assertFalse("Old suit should not contain quiz under test", suitEntity1.getQuizes().contains(quizEntity1));
        assertTrue("New suit should contain quiz under test", suitEntity3.getQuizes().contains(quizEntity1));
    }

    @Test
    public void testSetSuitWhenChangedQuizToEqual() {
        quizEntity1.setSuit(suitEntity1);

        QuizEntity quizEntity3 = new QuizEntity();
        quizEntity3.setId(quizEntity1.getId());

        quizEntity3.setSuit(suitEntity1);

        List<QuizEntity> quizes = suitEntity1.getQuizes();

        assertTrue("Quiz should have been updated",
                quizes.get(quizes.indexOf(quizEntity1)) == quizEntity3);
    }
}
