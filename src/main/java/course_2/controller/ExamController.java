package course_2.controller;

import org.springframework.web.bind.annotation.*;
import course_2.model.Question;
import course_2.service.ExaminerService;

import java.util.Collection;

@RestController
@RequestMapping("/exam")

public class ExamController {

    private final ExaminerService service;

    public ExamController(ExaminerService service) {
        this.service = service;
    }

    @GetMapping("/get/{amount}")

    public Collection<Question> getQuestions(@PathVariable int amount) {
        return service.getQuestions(amount);
    }
}
