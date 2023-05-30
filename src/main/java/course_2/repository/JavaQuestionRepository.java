package course_2.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import course_2.exception.QuestionAlreadyAddedException;
import course_2.exception.QuestionNotFoundException;
import course_2.model.Question;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Repository
public class JavaQuestionRepository implements QuestionRepository {

    private final Set<Question> questions;

    public JavaQuestionRepository() {
        this.questions = new HashSet<>();
    }

    @PostConstruct
    public void init() {
        questions.add(new Question("Что такое «переменная»?", "Ответ в уроке 1.2"));
        questions.add(new Question("По каким параметрам переменные различаются?", "Ответ в уроке 1.2"));
        questions.add(new Question("Что означает “инициализация”?", "Ответ в уроке 1.2"));
        questions.add(new Question("Какие особенности инициализации вы можете назвать?", "Ответ в уроке 1.2"));
        questions.add(new Question("Что такое «цикл»?", "Ответ в уроке 1.4"));
        questions.add(new Question("Какие циклы вы знаете, в чем их отличия?", "Ответ в уроке 1.4"));
        questions.add(new Question("ЧЧто вы знаете о массивах?", "Ответ в уроке 1.5"));
        questions.add(new Question("Дайте определение строке", "Ответ в уроке 1.7"));
        questions.add(new Question("Какие основные свойства строки вы знаете?", "Ответ в уроке 1.7"));
        questions.add(new Question("Что такое «метод»?", "Ответ в уроке 1.8"));
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
        return Collections.unmodifiableSet(questions);
    }
}
