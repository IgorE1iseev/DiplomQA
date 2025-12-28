package ru.iteco.fmhandroid.ui.PageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.endsWith;

import static ru.iteco.fmhandroid.ui.Utils.waitDisplayed;

import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;

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

    @Step("Сортировка новостей в Панели управления")
    // Проверяется видимость и кликабельность иконки сортировки
    public void sortNewsButtonControlPanelClick() {
        onView(withId(sortNewsButtonControlPanel)).check(matches(allOf(isDisplayed(), isClickable())));
        onView(withId(sortNewsButtonControlPanel)).perform(ViewActions.click());
    }

    @Step("Нажатие на кнопку 'Добавить новость'")
    public void createNews() {
        // Проверка, что элемент виден и кликабелен. Нажатие и ожидание загрузки
        onView(withId(createNewsButton)).check(matches(allOf(isDisplayed(), isClickable())));
        onView(withId(createNewsButton)).perform(click());
        onView(isRoot()).perform(waitDisplayed(createNewsPage.getSaveButtonCreateNews(), 10000));
    }
    @Step("Поиск новости по заголовком и проверка ее видимости")
    public void searchNewsIsVisible(String text) {
        // Используем RecyclerViewActions для прокрутки
        onView(withId(R.id.news_list_recycler_view))
                .perform(RecyclerViewActions.scrollTo(hasDescendant(withText(text))));

        // Проверяем, что элемент отображается
        onView(withText(text)).check(matches(isDisplayed()));
    }
//    Предыдущая версия, с которой не находит валидную новость внизу списка, но все остальное проходит
//    public void searchNewsIsVisible(String text) {
//        ViewInteraction titleName = onView(allOf(withText(text), withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
//        titleName.check(matches(isDisplayed()));
//        titleName.check(matches(withText(endsWith(text))));
//    }
}
