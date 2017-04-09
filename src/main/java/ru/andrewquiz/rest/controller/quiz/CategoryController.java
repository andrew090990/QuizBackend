package ru.andrewquiz.rest.controller.quiz;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.andrewquiz.dto.quiz.Category;
import ru.andrewquiz.dto.quiz.Suit;

import javax.ws.rs.core.MediaType;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMethod;
import ru.andrewquiz.service.quiz.CategoryService;
import ru.andrewquiz.service.quiz.SuitService;

/**
 * Created by Andrew on 23.03.2017.
 */

@Controller
@RequestMapping("/categories")
public class CategoryController {

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
    public @ResponseBody List<Suit> getAllSuitsByCategory(@PathVariable long categoryId) {

        return suitService.getSuitsByCategoryId(categoryId);
    }


    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SuitService suitService;

    private Logger logger = Logger.getLogger(CategoryController.class);
}
