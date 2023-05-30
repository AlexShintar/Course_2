package course_2;

import course_2.exception.QuestionAlreadyAddedException;
import course_2.exception.QuestionNotFoundException;
import course_2.exception.QuestionsStorageIsEmptyException;
import course_2.model.Question;
import course_2.repository.MathQuestionRepository;
import course_2.service.MathQuestionService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class MathQuestionServiceTest {

    @Spy
    private MathQuestionRepository repository;
    @InjectMocks
    private MathQuestionService service;

    @BeforeEach
    public void beforeEach() {
        service.add("Вопрос 1", "Ответ 1");
        service.add("Вопрос 2", "Ответ 2");
        service.add("Вопрос 3", "Ответ 3");
    }

    @AfterEach
    public void afterEach() {
        service.getAll().stream().toList().forEach(service::remove);
    }

    @Test
    public void addTest() {
        int beforeCount = service.getAll().size();
        Question expected = new Question("Вопрос", "Ответ");
        Question expected2 = new Question("Вопрос 4", "Ответ 4");

        Assertions.assertThat(service.add("Вопрос", "Ответ"))
                .isEqualTo(expected)
                .isIn(service.getAll());
        Assertions.assertThat(service.add(expected2))
                .isEqualTo(expected2)
                .isIn(service.getAll());
        Assertions.assertThat(service.getAll()).hasSize(beforeCount + 2);
    }

    @Test
    public void addWhenAlreadyExistsTest() {
        Assertions.assertThatExceptionOfType(QuestionAlreadyAddedException.class)
                .isThrownBy(() -> service.add("Вопрос 1", "Ответ 1"));
    }

    @Test
    public void removeTest() {
        int beforeCount = service.getAll().size();
        Question expected = new Question("Вопрос 1", "Ответ 1");

        Assertions.assertThat(service.remove(expected))
                .isEqualTo(expected)
                .isNotIn(service.getAll());
        Assertions.assertThat(service.getAll()).hasSize(beforeCount - 1);
        Assertions.assertThatExceptionOfType(QuestionNotFoundException.class)
                .isThrownBy(() -> service.remove(expected));
    }

    @Test
    public void removeWhenNotFoundTest() {
        Question expected = new Question("Вопрос", "Ответ");
        Assertions.assertThatExceptionOfType(QuestionNotFoundException.class)
                .isThrownBy(() -> service.remove(expected));
    }

    @Test
    public void getAllTest() {
        Assertions.assertThat(service.getAll())
                .hasSize(3)
                .containsExactlyInAnyOrder(
                        new Question("Вопрос 1", "Ответ 1"),
                        new Question("Вопрос 2", "Ответ 2"),
                        new Question("Вопрос 3", "Ответ 3")
                );
    }

    @Test
    public void getRandomQuestionTest() {
        Assertions.assertThat(service.getRandomQuestion())
                .isIn(service.getAll());
        service.getAll().stream().toList().forEach(service::remove);
        Assertions.assertThatExceptionOfType(QuestionsStorageIsEmptyException.class)
                .isThrownBy(service::getRandomQuestion);
    }
}
