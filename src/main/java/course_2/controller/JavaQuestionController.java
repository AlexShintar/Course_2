package course_2.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import course_2.model.Question;
import course_2.service.QuestionService;

import java.util.Collection;

@RestController
@RequestMapping("/exam/java")

public class JavaQuestionController {
    private final QuestionService service;

    public JavaQuestionController(QuestionService service) {
        this.service = service;
    }

    @GetMapping("/add")

    public Question addQuestion(
            @RequestParam String question,
            @RequestParam String answer) {
        return service.add(question, answer);
    }

    @GetMapping()
    public Collection<Question> getQuestions() {
        return service.getAll();
    }

    @GetMapping("/remove")
    public Question removeQuestion(
            @RequestParam String question,
            @RequestParam String answer) {
        return service.remove(new Question(question, answer));
    }

}
