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
public class SuitEntityTest {

    @Autowired
    private SuitEntity suitEntity1;

    @Autowired
    private CategoryEntity categoryEntity1;

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

    @Test
    public void testSetCategoryWhenSetSomeCategory() {

        suitEntity1.setCategory(null);
        suitEntity1.setCategory(categoryEntity1);

        assertTrue("Category should be set", suitEntity1.getCategory() == categoryEntity1);
        assertTrue("Category should contain suit under test", categoryEntity1.getSuits().contains(suitEntity1));
    }

    @Test
    public void testSetCategoryWhenSetNull() {

        suitEntity1.setCategory(categoryEntity1);
        suitEntity1.setCategory(null);

        assertTrue("Category should be set to null", suitEntity1.getCategory() == null);
        assertFalse("Category should not contain suit under test", categoryEntity1.getChildCategories().contains(suitEntity1));
    }

    @Test
    public void testSetCategoryWhenChangedCategory() {

        suitEntity1.setCategory(categoryEntity1);
        CategoryEntity categoryEntity3 = new CategoryEntity();
        suitEntity1.setCategory(categoryEntity3);

        assertTrue("Category should be set to new", suitEntity1.getCategory() == categoryEntity3);
        assertFalse("Old category should not contain suit under test", categoryEntity1.getSuits().contains(suitEntity1));
        assertTrue("New category should contain suit under test", categoryEntity3.getSuits().contains(suitEntity1));
    }

    @Test
    public void testSetCategoryWhenChangedSuitToEqual() {
        suitEntity1.setCategory(categoryEntity1);

        SuitEntity suitEntity3 = new SuitEntity();
        suitEntity3.setId(suitEntity1.getId());

        suitEntity3.setCategory(categoryEntity1);

        List<SuitEntity> suits = categoryEntity1.getSuits();

        assertTrue("Suit should have been updated",
                suits.get(suits.indexOf(suitEntity1)) == suitEntity3);
    }
}
