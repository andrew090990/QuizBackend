package ru.andrewquiz.dao.quiz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by Andrew on 29.04.2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/appContext.xml"})
public class AnswerEntityTest {

    @Autowired
    private AnswerEntity answerEntity1;

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
}
