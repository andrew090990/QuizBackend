package ru.andrewquiz.rest.controller.quiz;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.andrewquiz.dto.quiz.Category;
import ru.andrewquiz.dto.quiz.Suit;

import javax.ws.rs.core.MediaType;
import java.util.List;

import ru.andrewquiz.service.quiz.CategoryService;
import ru.andrewquiz.service.quiz.SuitService;

/**
 * Created by Andrew on 23.03.2017.
 */

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    public CategoryController(CategoryService categoryService, SuitService suitService) {
        this.categoryService = categoryService;
        this.suitService = suitService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public @ResponseBody List<Category> getAllCategories() {

        return categoryService.getCategories();
    }

    @RequestMapping(value = "{categoryId:\\d+}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public @ResponseBody Category getCategory(@PathVariable long categoryId) {

        return categoryService.getCategory(categoryId);
    }

//    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
    @RequestMapping(value = "{categoryId:\\d+}/suits", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public @ResponseBody List<Suit> getSuitsByCategory(@PathVariable long categoryId) {

        return suitService.getSuitsByCategoryId(categoryId);
    }

    @RequestMapping(value = "{parentCategoryId:\\d+}/categories", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public @ResponseBody List<Category> getCategoriesByParentCategory(@PathVariable long parentCategoryId) {

        return categoryService.getCategoriesByParentCategoryId(parentCategoryId);
    }

    @Transactional
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public @ResponseBody Category postCategory(@RequestBody Category category) {

        Long id = categoryService.createCategory(category);

        return categoryService.getCategory(id);
    }

    @Transactional
    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public @ResponseBody Category putCategory(@RequestBody Category category) {

        categoryService.updateCategory(category);

        return categoryService.getCategory(category.getId());
    }


    private CategoryService categoryService;

    private SuitService suitService;

    private Logger logger = Logger.getLogger(CategoryController.class);
}
