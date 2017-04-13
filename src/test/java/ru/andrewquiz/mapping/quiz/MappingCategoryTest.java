package ru.andrewquiz.mapping.quiz;

import static org.junit.Assert.*;

import org.dozer.CustomConverter;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.andrewquiz.dao.quiz.CategoryEntity;
import ru.andrewquiz.dto.quiz.Category;
import ru.andrewquiz.mapper.CustomDozerBeanMapper;
import ru.andrewquiz.mapper.converter.quiz.LongCategoryConverter;
import ru.andrewquiz.repository.quiz.CategoryRepository;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

/**
 * Created by Andrew on 12.04.2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/appContext.xml"})
public class MappingCategoryTest {

    @Autowired
    private CustomDozerBeanMapper mapper;

    @Autowired
    @InjectMocks
    private LongCategoryConverter longCategoryConverter;

    @Mock
    private CategoryRepository repo;

    @Autowired
    private Category category2;

    @Autowired
    private CategoryEntity categoryEntity1;

    @Autowired
    private CategoryEntity categoryEntity2;

    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Before
    public void setConvertersWithMockedDependencies() {
        if (mapper.getCustomConvertersWithId().size() == 0) {
            Map<String, CustomConverter> converters = new HashMap<String, CustomConverter>();
            converters.put("LongCategoryConverter", longCategoryConverter);

            mapper.setCustomConvertersWithId(converters);
        }
    }

    @Test
    public void testCategoryToCategoryEntity() {

        when(repo.findOne(anyLong())).thenReturn(null);
        when(repo.findOne(category2.getParentCategoryId())).thenReturn(categoryEntity1);

        CategoryEntity categoryEntity = mapper.map(category2, CategoryEntity.class);

        assertEquals("name must be equal; name = " + String.valueOf(categoryEntity.getName()),
                category2.getName(), categoryEntity.getName());

        assertEquals("parentCategory.id must be equal to parentCategoryId",
                category2.getParentCategoryId(), categoryEntity.getParentCategory().getId());

        assertTrue("id must be null; id = " + String.valueOf(categoryEntity.getId()),
                categoryEntity.getId() == null);

    }

    @Test
    public void testCategoryEntityToCategory() {

        Category category = mapper.map(categoryEntity2, Category.class);

        assertEquals("name must be equal; name = " + String.valueOf(category.getName()),
                categoryEntity2.getName(), category.getName());

        assertEquals("parentCategoryId must be equal to parentCategory.id",
                categoryEntity2.getParentCategory().getId(), category.getParentCategoryId());

        assertEquals("id must be equal; id = " + String.valueOf(category.getId()),
                categoryEntity2.getId(), category.getId());

    }
}
