package course_2;

import course_2.exception.MathQuestionMethodNotAllowedException;
import course_2.service.MathQuestionGeneratorService;
import course_2.model.Question;
import course_2.service.MathQuestionService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class MathQuestionServiceTest {

    @Mock
    private MathQuestionGeneratorService questionGenerator;
    @InjectMocks
    private MathQuestionService service;

    @Test
    public void addTest() {
        Assertions.assertThatExceptionOfType(MathQuestionMethodNotAllowedException.class)
                .isThrownBy(() -> service.add("Вопрос 1", "Ответ 1"));
        Assertions.assertThatExceptionOfType(MathQuestionMethodNotAllowedException.class)
                .isThrownBy(() -> service.add(new Question("Вопрос 1", "Ответ 1")));
    }

    @Test
    public void removeTest() {
        Assertions.assertThatExceptionOfType(MathQuestionMethodNotAllowedException.class)
                .isThrownBy(() -> service.remove(new Question("Вопрос 1", "Ответ 1")));
    }

    @Test
    public void getAllTest() {
        Assertions.assertThatExceptionOfType(MathQuestionMethodNotAllowedException.class)
                .isThrownBy(() -> service.getAll());
    }
    @Test
    public void getRandomQuestionTest() {

        Mockito.when(questionGenerator.get()).thenReturn(
                new Question("Вопрос", "Ответ")
        );
        Assertions.assertThat(service.getRandomQuestion())
                .isEqualTo(new Question("Вопрос", "Ответ"));
    }
}
