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
public class CategoryEntityTest {

    @Autowired
    private CategoryEntity categoryEntity1;

    @Test
    public void testEqualsHashCode() {

        assertNotEquals("These should not be equal", categoryEntity1, null);
        assertNotEquals("These should not be equal", categoryEntity1, new Object());

        CategoryEntity categoryEntity2 = new CategoryEntity();
        assertNotEquals("These should not be equal", categoryEntity1, categoryEntity2);

        categoryEntity2.setId(categoryEntity1.getId());
        assertEquals("These should be equal", categoryEntity1, categoryEntity2);
        assertEquals("Hash codes should be equal", categoryEntity1.hashCode(), categoryEntity2.hashCode());
    }
}
