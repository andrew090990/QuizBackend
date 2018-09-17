package ru.andrewquiz.rest.controller.quiz;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.andrewquiz.dto.quiz.FullQuiz;
import ru.andrewquiz.dto.quiz.Quiz;
import ru.andrewquiz.service.quiz.FullQuizService;
import ru.andrewquiz.service.quiz.QuizService;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew on 16.04.2017.
 */

@Controller
@RequestMapping("/quizes")
public class QuizController {

    private QuizService quizService;

    private FullQuizService fullQuizService;

    private Logger logger = Logger.getLogger(QuizController.class);

    @Autowired
    public QuizController(QuizService quizService, FullQuizService fullQuizService) {
        this.quizService = quizService;
        this.fullQuizService = fullQuizService;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public @ResponseBody List<Quiz> getAllQuizes(@RequestParam(defaultValue = "true", required = false) boolean includeContent) {

        if (includeContent) {
            List<FullQuiz> listFullQuiz = fullQuizService.getAllDtos();
            return new ArrayList<Quiz>(listFullQuiz);
        } else {
            return quizService.getAllDtos();
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @RequestMapping(value = "{quizId:\\d+}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public @ResponseBody Quiz getQuiz(@PathVariable long quizId, @RequestParam(defaultValue = "true", required = false) boolean includeContent) {

        if (includeContent) {
            return fullQuizService.getDto(quizId);
        } else {
            return quizService.getDto(quizId);
        }
    }

    @Transactional
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public @ResponseBody FullQuiz postFullQuiz(@RequestBody FullQuiz fullQuiz) {

        Long id = fullQuizService.createEntity(fullQuiz);

        return fullQuizService.getDto(id);
    }

    @Transactional
    @RequestMapping(value = "{quizId:\\d+}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public @ResponseBody FullQuiz putFullQuiz(@RequestBody FullQuiz fullQuiz, @PathVariable long quizId) {

        fullQuizService.updateEntity(fullQuiz, quizId);

        return fullQuizService.getDto(quizId);
    }

    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "{quizId:\\d+}", method = RequestMethod.DELETE)
    public void deleteFullSuit(@PathVariable long quizId) {

        fullQuizService.deleteEntity(quizId);
    }

}
