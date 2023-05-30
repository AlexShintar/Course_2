package course_2.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import course_2.exception.IncorrectQuestionsAmountException;
import course_2.model.Question;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final QuestionService javaQuestionService;
    private final QuestionService mathQuestionService;

    public ExaminerServiceImpl(@Qualifier("javaQuestionService") QuestionService javaQuestionService,
                        @Qualifier("mathQuestionService") QuestionService mathQuestionService) {
        this.javaQuestionService = javaQuestionService;
        this.mathQuestionService = mathQuestionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {

        int jSize = javaQuestionService.getAll().size();
        int mSize = mathQuestionService.getAll().size();

        if (amount > jSize + mSize) {
            throw new IncorrectQuestionsAmountException();
        } else if (amount == jSize + mSize) {
            return Stream.concat(
                    javaQuestionService.getAll().stream(),
                    mathQuestionService.getAll().stream()
            ).collect(Collectors.toSet());
        }

        Set<Question> questions = new HashSet<>();

        int minJsize = Math.max((amount - mSize), 0);
        int rnd = minJsize + new Random().nextInt(jSize - minJsize + 1);

        while (questions.size() < (Math.min(rnd, amount))) {
            questions.add(javaQuestionService.getRandomQuestion());
        }
        while (questions.size() < amount) {
            questions.add(mathQuestionService.getRandomQuestion());
              }
        return questions;
    }
}