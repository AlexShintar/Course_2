package course_2.service;

import course_2.exception.QuestionsStorageIsEmptyException;
import course_2.repository.QuestionRepository;
import course_2.repository.JavaQuestionRepository;
import org.springframework.stereotype.Service;
import course_2.model.Question;

import java.util.Collection;
import java.util.Random;

@Service
public class JavaQuestionService implements QuestionService {
    private final QuestionRepository javaQuestionRepository;

    public JavaQuestionService(JavaQuestionRepository javaQuestionRepository) {
        this.javaQuestionRepository = javaQuestionRepository;
    }

    @Override
    public Question add(String question, String answer) {
        Question q = new Question(question, answer);
        javaQuestionRepository.add(q);
        return q;
    }

    @Override
    public Question add(Question question) {
        javaQuestionRepository.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        javaQuestionRepository.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return javaQuestionRepository.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        if (javaQuestionRepository.getAll().size() == 0) {
            throw new QuestionsStorageIsEmptyException();
        }
        return javaQuestionRepository.getAll().stream()
                .skip(new Random().nextInt(javaQuestionRepository.getAll().size()))
                .findFirst()
                .get();
    }
}
