package course_2.service;

import course_2.exception.QuestionAlreadyAddedException;
import course_2.exception.QuestionNotFoundException;
import course_2.exception.QuestionsStorageIsEmptyException;
import org.springframework.stereotype.Service;
import course_2.model.Question;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {

    private final Set<Question> questions;

    public JavaQuestionService() {
        this.questions = new HashSet<>();
    }

    @Override
    public Question add(String question, String answer) {
        Question q = new Question(question, answer);
        if (questions.contains(q)) {
            throw new QuestionAlreadyAddedException();
        }
        questions.add(q);
        return q;
    }

    @Override
    public Question add(Question question) {
        if (questions.contains(question)) {
            throw new QuestionAlreadyAddedException();
        }
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (questions.contains(question)) {
            questions.remove(question);
            return question;
        }
        throw new QuestionNotFoundException();
    }

    @Override
    public Collection<Question> getAll() {
        return questions;
    }

    @Override
    public Question getRandomQuestion() {
        if (questions.size() == 0) {
            throw new QuestionsStorageIsEmptyException();
        }
        return questions.stream()
                .skip(new Random().nextInt(questions.size()))
                .findFirst()
                .get();
    }
}
