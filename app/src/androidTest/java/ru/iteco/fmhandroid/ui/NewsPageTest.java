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

@Epic(value = "Страница 'Новости'")

public class NewsPageTest {
    AppBar appBar = new AppBar();
    MainPage mainPage = new MainPage();
    SuccessfulAuthorization successfulAuthorization = new SuccessfulAuthorization();
    NewsPage newsPage = new NewsPage();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setUp() {
        Espresso.onView(isRoot()).perform(Utils.waitDisplayed(appBar.getAppBarContainer(), 10000));
        if (!mainPage.mainPageIsDisplayed()) {
            successfulAuthorization.userAuthorization();
        }
    }

    @Feature(value = "Сортировка новостей")
    @Test
    public void sortingNews() {
        appBar.goToNews();
        newsPage.sortNewsButtonClick();
    }

    @Feature(value = "Открытие страницы 'Фильтровать новости'")
    @Test
    public void openPageFilterNews() {
        appBar.goToNews();
        newsPage.filterNewsOpen();
    }

    @Feature(value = "Открытие страницы 'Панель управления'")
    @Test
    public void openPageControlPanel() {
        appBar.goToNews();
        newsPage.controlPanelNewsOpen();
    }
}
