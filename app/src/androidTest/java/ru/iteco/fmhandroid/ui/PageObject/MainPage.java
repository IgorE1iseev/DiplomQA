package ru.iteco.fmhandroid.ui.PageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class MainPage {
    private final int containerListNews = R.id.container_list_news_include_on_fragment_main;
    public ViewInteraction textNewsOnMainPage = onView(withText("Новости"));
    private final int allNewsButton = R.id.all_news_text_view;
    public ViewInteraction textAllNews = onView(withText("Все новости"));

    public int getContainerListNews() {
        return containerListNews;
    }

    // проверяется видимость элемента containerListNews (раздел новости на главной)
    // true означает, что контейнер есть и пользователь находится на главной странице,
    // false означает, что пользователь не видит контейнер с новостями и находится на авторизации.
    public Boolean mainPageIsDisplayed() {
        Allure.step("Проверяем видимость элемента-контейнера с новостями");
        try {
            onView(withId(containerListNews)).check(matches(isDisplayed()));
            return true;
        } catch (NoMatchingViewException noMatchingViewException) {
            return false;
        }
    }

    public void newsPageIsDisplayed() {
        Allure.step("Видимость раздела 'Новости'");
        onView(withId(containerListNews)).check(matches(isDisplayed()));
        textNewsOnMainPage.check(matches(withText("Новости")));
    }

    public void clickAllNewsButton() {
        Allure.step("Нажатие кнопки 'Все новости'");
        onView(withId(allNewsButton));
        textAllNews.check(matches(isDisplayed()));
        textAllNews.perform(click());
    }
}
