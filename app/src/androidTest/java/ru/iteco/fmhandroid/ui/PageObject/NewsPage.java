package ru.iteco.fmhandroid.ui.PageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;


import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.Utils;

public class NewsPage {
    FilterNewsPage filterNewsPage = new FilterNewsPage();
    public ViewInteraction textNewsOnPageNews = onView(withText("Новости"));
    private final int containerListPageNews = R.id.container_list_news_include;

    private final int sortNewsButton = R.id.sort_news_material_button;
    private final int filterNewsButton = R.id.filter_news_material_button;
    private final int editNewsButton = R.id.edit_news_material_button;
    ControlPanelPage controlPanelPage = new ControlPanelPage();

    public int getContainerListPageNews() {
        return containerListPageNews;
    }
    public int getFilterNewsButton() {
        return filterNewsButton;
    }
    @Step("Видимость элемента с текстом 'Новости'")
    public void newsPageElementIsVisible() {
        onView(withId(containerListPageNews)).check(matches(isDisplayed()));
        textNewsOnPageNews.check(matches(withText("Новости")));
    }

    @Step("Сортировка новостей")
    // проверяется видимость и кликабельность иконки сортировки
    public void sortNewsButtonClick() {
        onView(withId(sortNewsButton)).check(matches(allOf(isDisplayed(), isClickable())));
        onView(withId(sortNewsButton)).perform(ViewActions.click());
    }

    @Step("Открытие страницы 'Фильтровать новости'")
    // проверяется видимость и кликабельность иконки сортировки, нажатие и ожидание загрузки 7 секунд
    public void filterNewsOpen() {
        onView(withId(filterNewsButton)).check(matches(allOf(isDisplayed(), isClickable())));
        onView(withId(filterNewsButton)).perform(ViewActions.click());
        onView(isRoot()).perform(Utils.waitDisplayed(filterNewsPage.getFilter(), 7000));
    }

    @Step("Открытие страницы 'Панель управления'")
    public void controlPanelNewsOpen() {
        onView(isRoot()).perform(Utils.waitDisplayed(editNewsButton, 3000));
        onView(withId(editNewsButton)).check(matches(allOf(isDisplayed(), isClickable())));
        onView(withId(editNewsButton)).perform(ViewActions.click());
        onView(isRoot()).perform(Utils.waitDisplayed(controlPanelPage.getCreateNewsButton(), 7000));
    }

//    private final int containerLayoutControlPanel = R.id.layout_background_image_view;

//    @Step("Видимость элемента контейнера с текстом 'Панель управления'")
//    public void visibilityOfControlPanelLabel() {
//        onView(withId(containerLayoutControlPanel)).check(matches(isDisplayed()));
//        onView(isRoot()).perform(Utils.waitDisplayed(containerLayoutControlPanel, 3000));
//    }

}
