package course_2;

import course_2.exception.IncorrectQuestionsAmountException;
import course_2.model.Question;
import course_2.service.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest {
    @Mock
    private JavaQuestionService javaQuestionService;
    @Mock
    private MathQuestionService mathQuestionService;

    public static Stream<Arguments> getQuestionsTestParams() {
        return Stream.of(
                Arguments.of(1,
                        Set.of(
                                new Question("Вопрос 1", "Ответ 1"),
                                new Question("Вопрос 4", "Ответ 4"))
                ),
                Arguments.of(2,
                        Set.of(
                                new Question("Вопрос 1", "Ответ 1"),
                                new Question("Вопрос 2", "Ответ 2"),
                                new Question("Вопрос 4", "Ответ 4"),
                                new Question("Вопрос 5", "Ответ 5"))
                ),
                Arguments.of(6,
                        Set.of(
                                new Question("Вопрос 1", "Ответ 1"),
                                new Question("Вопрос 2", "Ответ 2"),
                                new Question("Вопрос 3", "Ответ 3"),
                                new Question("Вопрос 4", "Ответ 4"),
                                new Question("Вопрос 5", "Ответ 5"),
                                new Question("Вопрос 6", "Ответ 6"))
                )
        );
    }

    private ExaminerServiceImpl examinerService;

    @BeforeEach
    public void beforeEach() {
        examinerService = new ExaminerServiceImpl(javaQuestionService, mathQuestionService);

        Mockito.lenient().when(javaQuestionService.getAll()).thenReturn(
                Set.of(
                        new Question("Вопрос 1", "Ответ 1"),
                        new Question("Вопрос 2", "Ответ 2"),
                        new Question("Вопрос 3", "Ответ 3")
                )
        );
        Mockito.lenient().when(mathQuestionService.getAll()).thenReturn(
                Set.of(
                        new Question("Вопрос 4", "Ответ 4"),
                        new Question("Вопрос 5", "Ответ 5"),
                        new Question("Вопрос 6", "Ответ 6")
                )
        );
    }

    @Test
    public void getQuestionsWhenBigAmountTest() {
        Assertions.assertThatExceptionOfType(IncorrectQuestionsAmountException.class)
                .isThrownBy(() -> examinerService.getQuestions(7));
    }

    @ParameterizedTest
    @MethodSource("getQuestionsTestParams")

    public void getQuestionsTest(int amount, Set<Question> expected) {

        Mockito.lenient().when(javaQuestionService.getRandomQuestion()).thenReturn(
                new Question("Вопрос 1", "Ответ 1"),
                new Question("Вопрос 2", "Ответ 2"),
                new Question("Вопрос 3", "Ответ 3")
        );
        Mockito.lenient().when(mathQuestionService.getRandomQuestion()).thenReturn(
                new Question("Вопрос 4", "Ответ 4"),
                new Question("Вопрос 5", "Ответ 5"),
                new Question("Вопрос 6", "Ответ 6")
        );

        Collection<Question> test = examinerService.getQuestions(amount);
        Assertions.assertThat(expected)
                .containsAll(test);
        Assertions.assertThat(test.size())
                .isEqualTo(amount);
    }
}
