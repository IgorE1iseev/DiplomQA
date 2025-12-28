package ru.iteco.fmhandroid.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;

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
import ru.iteco.fmhandroid.ui.PageObject.ControlPanelPage;
import ru.iteco.fmhandroid.ui.PageObject.MainPage;
import ru.iteco.fmhandroid.ui.PageObject.NewsPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)

@Epic(value = "'Панель управления' в разделе 'Новости'")

public class ControlPanelTest {
    MainPage mainPage = new MainPage();
    AppBar appBar = new AppBar();
    SuccessfulAuthorization successfulAuthorization = new SuccessfulAuthorization();
    NewsPage newsPage = new NewsPage();
    ControlPanelPage controlPanelPage = new ControlPanelPage();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setUp() {
        onView(isRoot()).perform(Utils.waitDisplayed(appBar.getAppBarContainer(), 10000));
        if (!mainPage.mainPageIsDisplayed()) {
            successfulAuthorization.userAuthorization();
        }
    }
    @Feature(value = "Сортировка новостей в панели управления")
    @Test
    public void sortingNewsControlPanel() {
        appBar.goToNews();
        newsPage.controlPanelNewsOpen();
        controlPanelPage.sortNewsButtonControlPanelClick();
    }

    @Feature(value = "Открытие создания новости в панели управления")
    @Test
    public void openCreateNewsPage() {
        appBar.goToNews();
        newsPage.controlPanelNewsOpen();
        controlPanelPage.createNews();
    }
}
