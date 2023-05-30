package course_2.service;

import course_2.exception.QuestionsStorageIsEmptyException;
import org.springframework.stereotype.Service;
import course_2.model.Question;
import course_2.repository.MathQuestionRepository;
import course_2.repository.QuestionRepository;

import java.util.Collection;
import java.util.Random;

@Service
public class MathQuestionService implements QuestionService {
    private final QuestionRepository mathQuestionRepository;

    public MathQuestionService(MathQuestionRepository mathQuestionRepository) {
        this.mathQuestionRepository = mathQuestionRepository;
    }

    @Override
    public Question add(String question, String answer) {
        Question q = new Question(question, answer);
        mathQuestionRepository.add(q);
        return q;
    }

    @Override
    public Question add(Question question) {
        mathQuestionRepository.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        mathQuestionRepository.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return mathQuestionRepository.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        if (mathQuestionRepository.getAll().size() == 0) {
            throw new QuestionsStorageIsEmptyException();
        }
        return mathQuestionRepository.getAll().stream()
                .skip(new Random().nextInt(mathQuestionRepository.getAll().size()))
                .findFirst()
                .get();
    }
}
