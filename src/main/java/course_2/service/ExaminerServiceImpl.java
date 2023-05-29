package course_2.service;

import org.springframework.stereotype.Service;
import course_2.exception.IncorrectQuestionsAmountException;
import course_2.model.Question;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {

        if (amount > questionService.getAll().size()) {
            throw new IncorrectQuestionsAmountException();
        } else if (amount == questionService.getAll().size()) {
            return questionService.getAll();
        }

        Set<Question> questions = new HashSet<>();
        while (questions.size() < amount) {
            questions.add(questionService.getRandomQuestion());
        }
        return questions;
    }
}