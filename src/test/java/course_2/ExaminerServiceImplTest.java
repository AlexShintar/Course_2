package course_2;

import course_2.exception.IncorrectQuestionsAmountException;
import course_2.model.Question;
import course_2.service.ExaminerServiceImpl;
import course_2.service.QuestionService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest {
    @Mock
    private QuestionService questionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    public static Stream<Arguments> getQuestionsTestParams() {
        return Stream.of(
                Arguments.of(1,
                        Set.of(
                                new Question("Вопрос 1", "Ответ 1"))
                ),
                Arguments.of(2,
                        Set.of(
                                new Question("Вопрос 1", "Ответ 1"),
                                new Question("Вопрос 2", "Ответ 2"))
                ),
                Arguments.of(3,
                        Set.of(
                                new Question("Вопрос 1", "Ответ 1"),
                                new Question("Вопрос 2", "Ответ 2"),
                                new Question("Вопрос 3", "Ответ 3"))
                )
        );
    }

    @BeforeEach
    public void beforeEach() {
        Mockito.when(questionService.getAll()).thenReturn(
                Set.of(
                        new Question("Вопрос 1", "Ответ 1"),
                        new Question("Вопрос 2", "Ответ 2"),
                        new Question("Вопрос 3", "Ответ 3")
                )
        );
    }

    @Test
    public void getQuestionsWhenBigAmountTest() {
        Assertions.assertThatExceptionOfType(IncorrectQuestionsAmountException.class)
                .isThrownBy(() -> examinerService.getQuestions(4));
    }

    @ParameterizedTest
    @MethodSource("getQuestionsTestParams")

    public void getQuestionsTest(int amount, Set<Question> expected) {
        Mockito.lenient().when(questionService.getRandomQuestion()).thenReturn(
                new Question("Вопрос 1", "Ответ 1"),
                new Question("Вопрос 1", "Ответ 1"),
                new Question("Вопрос 2", "Ответ 2")
        );
        Assertions.assertThat(examinerService.getQuestions(amount))
                .containsExactlyInAnyOrderElementsOf(expected);
    }
}
