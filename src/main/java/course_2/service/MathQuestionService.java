package course_2.service;

import course_2.exception.MathQuestionMethodNotAllowedException;
import org.springframework.stereotype.Service;
import course_2.model.Question;
import java.util.Collection;


@Service
public class MathQuestionService implements QuestionService {
private final MathQuestionGeneratorService questionGenerator;
    public MathQuestionService(MathQuestionGeneratorService questionGenerator) {
        this.questionGenerator = questionGenerator;
    }
    @Override
    public Question add(String question, String answer) {
       throw new MathQuestionMethodNotAllowedException();
    }

    @Override
    public Question add(Question question) {
        throw new MathQuestionMethodNotAllowedException();
    }

    @Override
    public Question remove(Question question) {
        throw new MathQuestionMethodNotAllowedException();
    }

    @Override
    public Collection<Question> getAll() {
        throw new MathQuestionMethodNotAllowedException();
    }

    @Override
    public Question getRandomQuestion() {
        return questionGenerator.get();
    }
}
