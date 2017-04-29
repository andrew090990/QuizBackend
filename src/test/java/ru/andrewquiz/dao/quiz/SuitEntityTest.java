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
public class SuitEntityTest {

    @Autowired
    private SuitEntity suitEntity1;

    @Test
    public void testEqualsHashCode() {

        assertNotEquals("These should not be equal", suitEntity1, null);
        assertNotEquals("These should not be equal", suitEntity1, new Object());

        SuitEntity suitEntity2 = new SuitEntity();
        assertNotEquals("These should not be equal", suitEntity1, suitEntity2);

        suitEntity2.setId(suitEntity1.getId());
        assertEquals("These should be equal", suitEntity1, suitEntity2);
        assertEquals("Hash codes should be equal", suitEntity1.hashCode(), suitEntity2.hashCode());
    }
}
