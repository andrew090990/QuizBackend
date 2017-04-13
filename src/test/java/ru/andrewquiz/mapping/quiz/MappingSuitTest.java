package ru.andrewquiz.mapping.quiz;

import org.dozer.CustomConverter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.andrewquiz.dao.quiz.CategoryEntity;
import ru.andrewquiz.dao.quiz.SuitEntity;
import ru.andrewquiz.dto.quiz.Category;
import ru.andrewquiz.dto.quiz.Suit;
import ru.andrewquiz.mapper.CustomDozerBeanMapper;
import ru.andrewquiz.mapper.converter.quiz.LongCategoryConverter;
import ru.andrewquiz.repository.quiz.CategoryRepository;
import ru.andrewquiz.repository.quiz.SuitRepository;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

/**
 * Created by Andrew on 13.04.2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/appContext.xml"})
public class MappingSuitTest {

    @Autowired
    private CustomDozerBeanMapper mapper;

    @Autowired
    @InjectMocks
    private LongCategoryConverter longCategoryConverter;

    @Mock
    private CategoryRepository repo;

    @Autowired
    private Suit suit1;

    @Autowired
    private SuitEntity suitEntity1;

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
    public void testSuitToSuitEntity() {

        when(repo.findOne(anyLong())).thenReturn(null);
        when(repo.findOne(suit1.getCategoryId())).thenReturn(categoryEntity2);

        SuitEntity suitEntity = mapper.map(suit1, SuitEntity.class);

        assertEquals("name must be equal; name = " + String.valueOf(suitEntity.getName()),
                suit1.getName(), suitEntity.getName());

        assertEquals("category.id must be equal to categoryId",
                suit1.getCategoryId(), suitEntity.getCategory().getId());

        assertTrue("id must be null; id = " + String.valueOf(suitEntity.getId()),
                suitEntity.getId() == null);

    }

    @Test
    public void testSuitEntityToSuit() {

        Suit suit = mapper.map(suitEntity1, Suit.class);

        assertEquals("name must be equal; name = " + String.valueOf(suit.getName()),
                suitEntity1.getName(), suit.getName());

        assertEquals("categoryId must be equal to category.id",
                suitEntity1.getCategory().getId(), suit.getCategoryId());

        assertEquals("id must be equal; id = " + String.valueOf(suit.getId()),
                suitEntity1.getId(), suit.getId());

    }
}
