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
import ru.iteco.fmhandroid.ui.PageObject.CreateNewsPage;
import ru.iteco.fmhandroid.ui.PageObject.MainPage;
import ru.iteco.fmhandroid.ui.PageObject.NewsPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)

@Epic(value = "'Создание новости' в разделе 'Новости'")

public class CreateNewsTest {
    AppBar appBar = new AppBar();
    MainPage mainPage = new MainPage();
    SuccessfulAuthorization successfulAuthorization = new SuccessfulAuthorization();
    NewsPage newsPage = new NewsPage();
    ControlPanelPage controlPanelPage = new ControlPanelPage();
    CreateNewsPage createNewsPage = new CreateNewsPage();
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
    @Feature(value = "Создание новости валидными данными")
    @Test
    public void validNewsCreate() {
        appBar.goToNews();
        newsPage.controlPanelNewsOpen();
        controlPanelPage.createNews();
        // Добавление категории, заголовка, даты, времени, описания, нажатие на кнопку сохранения
        createNewsPage.chooseCategoryCreateNews("Массаж");
        createNewsPage.enterTitleCreateNews("testTitle");
        createNewsPage.enterDateCreateNews(Utils.currentDate());
        createNewsPage.enterTimeCreateNews("20:00");
        createNewsPage.enterDescriptionCreateNews("тестовое описание");
        createNewsPage.saveNewsButton();
//        Новость появляется внизу списка, который кто-то нагенерировал, требуется нажатие сортировки
//        controlPanelPage.sortNewsButtonControlPanelClick();
        // Проверка, что новость появилась в списке, используется RecyclerView
        controlPanelPage.searchNewsIsVisible("testTitle");
    }
    @Feature(value = "Создание новости пустыми полями")
    @Test
    public void emptyFieldsNewsCreate() {
        appBar.goToNews();
        newsPage.controlPanelNewsOpen();
        controlPanelPage.createNews();
        createNewsPage.saveNewsButton();
        // Проверка, что форма создание новости все еще видна
        createNewsPage.newsCreationFormIsVisible();
    }
//    Тест упадет, если насоздавать новостей с одинаковыми заголовками
    @Feature(value = "Создание новости невалидными данными (дата в будущем)")
    @Test
    public void invalidNewsCreate() {
        appBar.goToNews();
        newsPage.controlPanelNewsOpen();
        controlPanelPage.createNews();
        // Добавление категории,заголовка, даты в будущем, времени, описания, нажатие на кнопку сохранения
        createNewsPage.chooseCategoryCreateNews("Праздник");
        createNewsPage.enterTitleCreateNews("futureTitle");
        createNewsPage.enterDateCreateNews(Utils.dateMore1Years());
        createNewsPage.enterTimeCreateNews("20:00");
        createNewsPage.enterDescriptionCreateNews("будущее");
        createNewsPage.saveNewsButton();
        // Проверка, что новость появилась в списке
        controlPanelPage.searchNewsIsVisible("futureTitle");
    }

}
