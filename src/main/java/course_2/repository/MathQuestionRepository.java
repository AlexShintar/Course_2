package course_2.repository;

import course_2.exception.QuestionAlreadyAddedException;
import course_2.exception.QuestionNotFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import course_2.model.Question;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Repository
public class MathQuestionRepository implements QuestionRepository {

    private final Set<Question> questions;

    public MathQuestionRepository() {
        this.questions = new HashSet<>();
    }

    @PostConstruct
    public void init() {
        questions.add(new Question("Какое значение наибольшего корня уравнения x^2−5x+4=0",
                "Корни уравнения x1=1 и x2=4. Очевидно, что больший из них 4."));
        questions.add(new Question("Найдите решения квадратного уравнения x^2−13x+12=0",
                "Корни x1=1 и x2=12."));
        questions.add(new Question("Найдите наименьший корень уравнения x^2−12x+35=0",
                "-10"));
        questions.add(new Question("Найдите разницу корней квадратного уравнения x^2−9x+20=0",
                "Решения этого уравнения 5 и 4. Их разница равна 1."));
        questions.add(new Question("Представьте 2.7 в виде процентов.",
                "270%"));
        questions.add(new Question("Сумма трех последовательных четных чисел ровна 78. Найди эти три числа.",
                "24, 26, 28"));
        questions.add(new Question("Вычислите значение выражения: x2 − 3xm − m + 4, для x = 2 и m = 3",
                "-13"));
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
