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
public class CategoryEntityTest {

    @Autowired
    private CategoryEntity categoryEntity1;

    @Autowired
    private CategoryEntity categoryEntity2;

    @Test
    public void testEqualsHashCode() {

        assertNotEquals("These should not be equal", categoryEntity1, null);
        assertNotEquals("These should not be equal", categoryEntity1, new Object());

        CategoryEntity categoryEntity3 = new CategoryEntity();
        assertNotEquals("These should not be equal", categoryEntity1, categoryEntity3);

        categoryEntity3.setId(categoryEntity1.getId());
        assertEquals("These should be equal", categoryEntity1, categoryEntity3);
        assertEquals("Hash codes should be equal", categoryEntity1.hashCode(), categoryEntity3.hashCode());
    }

    @Test
    public void testSetParentCategoryWhenSetSomeParent() {

        CategoryEntity categoryEntity3 = new CategoryEntity();
        categoryEntity3.setParentCategory(categoryEntity1);

        assertTrue("Parent should be set", categoryEntity3.getParentCategory() == categoryEntity1);
        assertTrue("Parent should contain child under test", categoryEntity1.getChildCategories().contains(categoryEntity3));
    }

    @Test
    public void testSetParentCategoryWhenSetNull() {

        categoryEntity2.setParentCategory(categoryEntity1);
        categoryEntity2.setParentCategory(null);

        assertTrue("Parent should be set to null", categoryEntity2.getParentCategory() == null);
        assertFalse("Parent should not contain child under test", categoryEntity1.getChildCategories().contains(categoryEntity2));
    }

    @Test
    public void testSetParentCategoryWhenChangedParent() {

        categoryEntity2.setParentCategory(categoryEntity1);
        CategoryEntity categoryEntity3 = new CategoryEntity();
        categoryEntity2.setParentCategory(categoryEntity3);

        assertTrue("Parent should be set to new", categoryEntity2.getParentCategory() == categoryEntity3);
        assertFalse("Old parent should not contain child under test", categoryEntity1.getChildCategories().contains(categoryEntity2));
        assertTrue("New parent should contain child under test", categoryEntity3.getChildCategories().contains(categoryEntity2));
    }

    @Test
    public void testSetParentCategoryWhenChangedChildToEqual() {
        categoryEntity2.setParentCategory(categoryEntity1);

        CategoryEntity categoryEntity3 = new CategoryEntity();
        categoryEntity3.setId(categoryEntity2.getId());

        categoryEntity3.setParentCategory(categoryEntity1);

        List<CategoryEntity> children = categoryEntity1.getChildCategories();

        assertTrue("Child should have been updated",
                children.get(children.indexOf(categoryEntity2)) == categoryEntity3);
    }
}
