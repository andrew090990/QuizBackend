package ru.andrewquiz.rest.controller.quiz;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.andrewquiz.dto.quiz.Category;
import ru.andrewquiz.dto.quiz.Suit;
import ru.andrewquiz.service.quiz.CategoryService;
import ru.andrewquiz.service.quiz.SuitService;

import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Andrew on 23.03.2017.
 */

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private CategoryService categoryService;

    private SuitService suitService;

    private Logger logger = Logger.getLogger(CategoryController.class);

    @Autowired
    public CategoryController(CategoryService categoryService, SuitService suitService) {
        this.categoryService = categoryService;
        this.suitService = suitService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public @ResponseBody List<Category> getAllCategories() {

        return categoryService.getAllDtos();
    }

    @RequestMapping(value = "{categoryId:\\d+}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public @ResponseBody Category getCategory(@PathVariable long categoryId) {

        return categoryService.getDto(categoryId);
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

        Long id = categoryService.createEntity(category);

        return categoryService.getDto(id);
    }

    @Transactional
    @RequestMapping(value = "{categoryId:\\d+}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public @ResponseBody Category putCategory(@RequestBody Category category, @PathVariable long categoryId) {

        categoryService.updateEntity(category, categoryId);

        return categoryService.getDto(categoryId);
    }

    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "{categoryId:\\d+}", method = RequestMethod.DELETE)
    public void deleteCategory(@PathVariable long categoryId) {

        categoryService.deleteEntity(categoryId);
    }
}
