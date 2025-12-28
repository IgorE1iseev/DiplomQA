package ru.iteco.fmhandroid.ui;

import static androidx.test.espresso.matcher.ViewMatchers.isRoot;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.Feature;
import ru.iteco.fmhandroid.ui.PageObject.AppBar;
import ru.iteco.fmhandroid.ui.PageObject.MainPage;
import ru.iteco.fmhandroid.ui.PageObject.NewsPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)

@Epic(value = "Страница 'Главная'")

public class MainMenuTest {
    AppBar appBar = new AppBar();
    MainPage mainPage = new MainPage();
    NewsPage newsPage = new NewsPage();
    SuccessfulAuthorization successfulAuthorization = new SuccessfulAuthorization();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    //Ждём появления верхней панели AppBar на экране 10 секунд. Если не отображается mainPage, то пользователь авторизуется
    public void setUp() {
        Espresso.onView(isRoot()).perform(Utils.waitDisplayed(appBar.getAppBarContainer(), 10000));
        if (!mainPage.mainPageIsDisplayed()) {
            successfulAuthorization.userAuthorization();
        }
    }

    @Feature(value = "Переход в 'Новости' через 'ВСЕ НОВОСТИ'")
    @Test
    public void openPageNewsByAllNews() {
        mainPage.clickAllNewsButton();
        newsPage.newsPageElementIsVisible();
    }
}
