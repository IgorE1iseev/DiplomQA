package ru.iteco.fmhandroid.ui.PageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Step;
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
    @Step("Видимость элемента-контейнера с новостями")
    public Boolean mainPageIsDisplayed() {
        try {
            onView(withId(containerListNews)).check(matches(isDisplayed()));
            return true;
        } catch (NoMatchingViewException noMatchingViewException) {
            return false;
        }
    }

    @Step("Видимость раздела 'Новости'")
    public void newsPageIsDisplayed() {
        onView(withId(containerListNews)).check(matches(isDisplayed()));
        textNewsOnMainPage.check(matches(withText("Новости")));
    }
    
    @Step("Нажатие кнопки 'Все новости'")
    public void clickAllNewsButton() {
        onView(withId(allNewsButton));
        textAllNews.check(matches(isDisplayed()));
        textAllNews.perform(click());
    }
}
