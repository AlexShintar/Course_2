package course_2.service;

import course_2.model.Question;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
@Service
public class MathQuestionGeneratorService {
    public Question get() {

        int a = 0, b = 0, c = 0, d = 0;

        while (d <= 0) {
            a = rnd();
            b = rnd();
            c = rnd();
            d = b * b - 4 * a * c;
        }

        String equation = ((a == 1 ? "" : a) + "x^2" + (b < 0 ? b : (b == 1 ? "+" : "+" + b)) + "x" + (c < 0 ? c : "+" + c) + "=0");

        double x1 = (-b - Math.sqrt(d)) / (2 * a);
        double x2 = (-b + Math.sqrt(d)) / (2 * a);

        String baseAnswer = "Корни уравнения: x1 = " + x1 + " и x2 = " + x2 + ". ";

        List<String> questionGenerator = List.of(
                "Найдите решения квадратного уравнения " + equation,
                (rnd() > 0 ? "Какое значение наибольшего корня" : "Найдите наибольший корень") + " уравнения " + equation,
                (rnd() > 0 ? "Какое значение наименьшего корня" : "Найдите наименьший корень") + " уравнения " + equation,
                "Найдите сумму корней квадратного уравнения " + equation,
                "Найдите разницу корней квадратного уравнения " + equation,
                "Представьте " + Math.abs(a) + "." + Math.abs(b) + " в виде процентов",
                "Сумма трех последовательных четных чисел ровна " + (d * 12 + 6) + ". Найди эти три числа",
                "Сумма трех последовательных нечетных чисел ровна " + (d * 12 + 9) + ". Найди эти три числа"
        );

        List<String> answerGenerator = List.of(baseAnswer,
                baseAnswer + "Очевидно, что больший из них равен " + Math.max(x1, x2),
                baseAnswer + "Очевидно, что меньший из них равен " + Math.min(x1, x2),
                baseAnswer + "Их сумма равна " + (x1 + x2),
                baseAnswer + "Их разница равна " + (x1 - x2),
                "" + (Math.abs(a) * 100 + Math.abs(b) * 10) + "%",
                "" + (d * 4) + ", " + (d * 4 + 2) + ", " + (d * 4 + 4),
                "" + (d * 4 + 1) + ", " + (d * 4 + 3) + ", " + (d * 4 + 5)
        );

        int index = new Random().nextInt(questionGenerator.size());
        return new Question(questionGenerator.get(index), answerGenerator.get(index));
    }

    private static int rnd() {
        int rnd = 0;
        while (rnd == 0) {
            rnd = (new Random().nextInt(3) - 1) * (new Random().nextInt(10));
        }
        return rnd;
    }
}
