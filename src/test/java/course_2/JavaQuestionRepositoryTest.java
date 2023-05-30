package course_2;

import course_2.exception.QuestionAlreadyAddedException;
import course_2.exception.QuestionNotFoundException;
import course_2.model.Question;
import course_2.repository.JavaQuestionRepository;
import course_2.repository.QuestionRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class JavaQuestionRepositoryTest {

    private final QuestionRepository repository = new JavaQuestionRepository();

    @BeforeEach
    public void beforeEach() {
        repository.add(new Question("Вопрос 1", "Ответ 1"));
        repository.add(new Question("Вопрос 2", "Ответ 2"));
        repository.add(new Question("Вопрос 3", "Ответ 3"));
    }

    @AfterEach
    public void afterEach() {
        repository.getAll().stream().toList().forEach(repository::remove);
    }

    @Test
    public void addTest() {
        int beforeCount = repository.getAll().size();
        Question expected = new Question("Вопрос", "Ответ");
        Assertions.assertThat(repository.add(expected))
                .isEqualTo(expected)
                .isIn(repository.getAll());
        Assertions.assertThat(repository.getAll()).hasSize(beforeCount + 1);
    }

    @Test
    public void addWhenAlreadyExistsTest() {
        Assertions.assertThatExceptionOfType(QuestionAlreadyAddedException.class)
                .isThrownBy(() -> repository.add(new Question("Вопрос 1", "Ответ 1")));
    }

    @Test
    public void removeTest() {
        int beforeCount = repository.getAll().size();
        Question expected = new Question("Вопрос 1", "Ответ 1");

        Assertions.assertThat(repository.remove(expected))
                .isEqualTo(expected)
                .isNotIn(repository.getAll());
        Assertions.assertThat(repository.getAll()).hasSize(beforeCount - 1);
        Assertions.assertThatExceptionOfType(QuestionNotFoundException.class)
                .isThrownBy(() -> repository.remove(expected));
    }

    @Test
    public void removeWhenNotFoundTest() {
        Question expected = new Question("Вопрос", "Ответ");
        Assertions.assertThatExceptionOfType(QuestionNotFoundException.class)
                .isThrownBy(() -> repository.remove(expected));
    }

    @Test
    public void getAllTest() {
        Assertions.assertThat(repository.getAll())
                .hasSize(3)
                .containsExactlyInAnyOrder(
                        new Question("Вопрос 1", "Ответ 1"),
                        new Question("Вопрос 2", "Ответ 2"),
                        new Question("Вопрос 3", "Ответ 3")
                );
    }
}
