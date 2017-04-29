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
public class QuestionEntityTest {

    @Autowired
    private QuestionEntity questionEntity1;

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
}
