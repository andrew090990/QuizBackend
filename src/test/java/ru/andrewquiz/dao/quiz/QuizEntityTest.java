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
public class QuizEntityTest {

    @Autowired
    private QuizEntity quizEntity1;

    @Autowired
    private FullQuizEntity fullQuizEntity1;

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
}
