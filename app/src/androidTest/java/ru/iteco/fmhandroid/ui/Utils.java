package ru.iteco.fmhandroid.ui;

import static androidx.test.espresso.matcher.ViewMatchers.isRoot;

import android.view.View;

import org.hamcrest.Matcher;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeoutException;

import androidx.test.espresso.PerformException;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.util.HumanReadables;
import androidx.test.espresso.util.TreeIterables;

public class Utils {
    //метод позволяет ждать появления нужного элемента на экране в течение заданного времени
    public static ViewAction waitDisplayed(final int viewId, final long millis) {
        return new ViewAction() {

            //Метод, который сообщает Espresso, к какому View можно применять это действие
            @Override
            public Matcher<View> getConstraints() {
                return isRoot();
            }

            //Возвращает описание действия, которое будет использоваться в логах и сообщениях об ошибках
            @Override
            public String getDescription() {
                return "wait for a specific view with id <" + viewId + "> to be displayed during " + millis + " millis.";
            }

            // Вызов внутреннего метода perform. Запускает логику ожидания
            @Override
            public void perform(UiController uiController, View view) {
                performWaitDisplayed(uiController, view, viewId, millis, getDescription());
            }
        };
    }

    public static boolean isViewVisible(View view) {
        return view.isShown(); // Check if the view is currently visible on the screen
    }

    // Внутренний метод perform, реализующий логику ожидания
    private static void performWaitDisplayed(UiController uiController, View view, int viewId, long millis, String description) {
        // Гарантирует, что мы начинаем поиск элемента уже после того, как приложение обработало все предыдущие действия
        uiController.loopMainThreadUntilIdle();

        // Запоминаем текущее время и вычисляем время, до которого мы будем ждать
        final long startTime = System.currentTimeMillis();
        final long endTime = startTime + millis;

        // Выполняем цикл, пока текущее время меньше времени окончания ожидания
        do {
            // Проходимся по всем дочерним представлениям
            for (View child : TreeIterables.breadthFirstViewTraversal(view)) {
                //Для каждого дочернего View проверяем,что:
                //Его ID совпадает с тем, который ищем (viewId),
                //Он видим на экране (вызов метода isViewVisible(child).
                //Если такой элемент найден, метод завершается (return)
                if (child.getId() == viewId && isViewVisible(child)) {
                    return;
                }
            }
            // Ждем 50 миллисекунд перед следующей итерацией.На каждой итерации цикла мы приостанавливаем тест
            // на 50 миллисекунд, чтобы не загружать активным ожиданием. Это дает время приложению отрисовать изменения
            uiController.loopMainThreadForAtLeast(50);
        } while (System.currentTimeMillis() < endTime);

        // Если цикл do-while завершился из-за истечения времени, а элемент так и не был найден,
        // создает исключение PerformException, которое сообщает о неудачном выполнении действия
        // с указанием причины TimeoutException - таймаут.
        throw new PerformException.Builder()
                .withActionDescription(description)
                .withViewDescription(HumanReadables.describe(view))
                .withCause(new TimeoutException())
                .build();
    }

    //Группа методов генерирует строки с датами в формате "dd.MM.yyyy"
    //Возвращает строку с текущей датой
    public static String currentDate() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    //Возвращает строку с датой в прошлом (вчерашний день)
    public static String dateInPast() {
        return LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    //Возвращает строку с датой более чем на месяц вперед от текущей
    public static String dateMore1Month() {
        return LocalDate.now().plusMonths(1).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    //Возвращает строку с датой более чем на год вперед от текущей
    public static String dateMore1Years() {
        return LocalDate.now().plusYears(1).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
}
