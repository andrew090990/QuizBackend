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
public class FullQuizEntityTest {

    @Autowired
    private QuizEntity quizEntity1;

    @Autowired
    private FullQuizEntity fullQuizEntity1;

    @Test
    public void testEqualsHashCode() {

        assertNotEquals("These should not be equal", fullQuizEntity1, null);
        assertNotEquals("These should not be equal", fullQuizEntity1, new Object());

        FullQuizEntity fullQuizEntity2 = new FullQuizEntity();
        assertNotEquals("These should not be equal", fullQuizEntity1, fullQuizEntity2);

        assertNotEquals("These should not be equal", fullQuizEntity1, quizEntity1);

        fullQuizEntity2.setId(fullQuizEntity1.getId());
        assertEquals("These should be equal", fullQuizEntity1, fullQuizEntity2);
        assertEquals("Hash codes should be equal", fullQuizEntity1.hashCode(), fullQuizEntity2.hashCode());
    }
}
