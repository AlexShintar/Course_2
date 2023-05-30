package course_2.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import course_2.model.Question;
import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final List<QuestionService> questionServices;

    public ExaminerServiceImpl(@Qualifier("javaQuestionService") QuestionService javaQuestionService,
                               @Qualifier("mathQuestionService") QuestionService mathQuestionService) {

        this.questionServices = new ArrayList<>();
        questionServices.add(javaQuestionService);
        questionServices.add(mathQuestionService);
    }

    @Override
    public Collection<Question> getQuestions(int amount) {

        Set<Question> questions = new HashSet<>();
        while (questions.size() < amount) {
            questions.add(questionServices.get(new Random().nextInt(questionServices.size())).getRandomQuestion());
        }
        return questions;
    }
}
