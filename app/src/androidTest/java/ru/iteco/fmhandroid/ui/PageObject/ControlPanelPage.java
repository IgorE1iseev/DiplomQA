package ru.iteco.fmhandroid.ui.PageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static ru.iteco.fmhandroid.ui.Utils.Utils.waitDisplayed;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class ControlPanelPage {
    CreateNewsPage createNewsPage = new CreateNewsPage();
    private final int createNewsButton = R.id.add_news_image_view;
    private final int sortNewsButtonControlPanel = R.id.sort_news_material_button;
    private final int editNewsButton = R.id.edit_news_item_image_view;
    private final int deleteNewsButton = R.id.delete_news_item_image_view;

    //    Стандартная кнопка OK в AlertDialog с фиксированным ID
    public void clickOkButton() {
        onView(withId(android.R.id.button1))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public int getCreateNewsButton() {
        return createNewsButton;
    }

    // Проверяется видимость и кликабельность иконки сортировки
    public void sortNewsButtonControlPanelClick() {
        Allure.step("Сортировка новостей в Панели управления");
        onView(withId(sortNewsButtonControlPanel)).check(matches(allOf(isDisplayed(), isClickable())));
        onView(withId(sortNewsButtonControlPanel)).perform(ViewActions.click());
    }

    public void createNews() {
        Allure.step("Нажатие на кнопку 'Добавить новость'");
        // Проверка, что элемент виден и кликабелен. Нажатие и ожидание загрузки
        onView(withId(createNewsButton)).check(matches(allOf(isDisplayed(), isClickable())));
        onView(withId(createNewsButton)).perform(click());
        onView(isRoot()).perform(waitDisplayed(createNewsPage.getSaveButtonCreateNews(), 10000));
    }

    public void searchNewsIsVisible(String text) {
        Allure.step("Поиск новости по заголовку и проверка ее видимости");
        // Используем RecyclerViewActions для прокрутки
        onView(withId(R.id.news_list_recycler_view))
                .perform(RecyclerViewActions.scrollTo(hasDescendant(withText(text))));
        // Проверяем, что элемент отображается
        onView(withText(text)).check(matches(isDisplayed()));
    }
}
