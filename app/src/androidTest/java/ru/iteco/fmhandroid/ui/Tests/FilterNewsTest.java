package ru.iteco.fmhandroid.ui.Tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.Feature;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.PageObject.AppBar;
import ru.iteco.fmhandroid.ui.PageObject.FilterNewsPage;
import ru.iteco.fmhandroid.ui.PageObject.MainPage;
import ru.iteco.fmhandroid.ui.PageObject.NewsPage;
import ru.iteco.fmhandroid.ui.Utils.SuccessfulAuthorization;
import ru.iteco.fmhandroid.ui.Utils.TestData;
import ru.iteco.fmhandroid.ui.Utils.Utils;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)

@Epic(value = "Фильтровать новости")

public class FilterNewsTest {
    AppBar appBar = new AppBar();
    MainPage mainPage = new MainPage();
    SuccessfulAuthorization successfulAuthorization = new SuccessfulAuthorization();
    NewsPage newsPage = new NewsPage();
    FilterNewsPage filterNewsPage = new FilterNewsPage();


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

    @Feature(value = "Выбор категории")
    @Test
    public void enterCategoryFromList() {
        appBar.goToNews();
        newsPage.filterNewsOpen();
//        Создаётся список строк (List<String>), содержащий названия категорий новостей.
//        Arrays.asList заполняет значениями
        List<String> categories = Arrays.asList(
                TestData.CATEGORY_ANNOUNCEMENT,
                TestData.CATEGORY_BIRTHDAY,
                TestData.CATEGORY_SALARY,
                TestData.CATEGORY_UNION,
                TestData.CATEGORY_HOLIDAY,
                TestData.CATEGORY_MASSAGE,
                TestData.CATEGORY_THANKS,
                TestData.CATEGORY_HELP
        );
//        Берёт каждый элемент из списка и сохраняет в переменную category.
        for (String category : categories) {
            filterNewsPage.chooseCategory(category);
        }
    }

    @Feature(value = "Фильтрация новостей при валидных данных")
    @Test
    public void filterNewsByValidData() {
        appBar.goToNews();
        newsPage.filterNewsOpen();
        filterNewsPage.chooseCategory(TestData.CATEGORY_ANNOUNCEMENT);
        filterNewsPage.chooseDateStart(Utils.currentDate());
        filterNewsPage.chooseDateEnd(Utils.currentDate());
        filterNewsPage.applyFilter();
        newsPage.newsPageElementIsVisible();
    }

    @Feature(value = "Фильтрация новостей при пустых полях")
    @Test
    public void filterNewsByEmptyFields() {
        appBar.goToNews();
        newsPage.filterNewsOpen();
        filterNewsPage.chooseCategory(TestData.EMPTY_DATA);
        filterNewsPage.chooseDateStart(TestData.EMPTY_DATA);
        filterNewsPage.chooseDateEnd(TestData.EMPTY_DATA);
        filterNewsPage.applyFilter();
        newsPage.newsPageElementIsVisible();
    }

    @Feature(value = "Фильтрация новостей по категории")
    @Test
    public void filterNewsByCategory() {
        appBar.goToNews();
        newsPage.filterNewsOpen();
        filterNewsPage.chooseCategory(TestData.CATEGORY_BIRTHDAY);
        filterNewsPage.applyFilter();
        newsPage.newsPageElementIsVisible();
    }

    @Feature(value = "Фильтрация новостей по текущей дате")
    @Test
    public void filterNewsByDate() {
        appBar.goToNews();
        newsPage.filterNewsOpen();
        filterNewsPage.chooseDateStart(Utils.currentDate());
        filterNewsPage.chooseDateEnd(Utils.currentDate());
        filterNewsPage.applyFilter();
        newsPage.newsPageElementIsVisible();
    }

    @Feature(value = "Отмена фильтра. Выход со страницы фильтрации")
    @Test
    public void cancelFilter() {
        appBar.goToNews();
        newsPage.filterNewsOpen();
        filterNewsPage.chooseCategory(TestData.CATEGORY_ANNOUNCEMENT);
        filterNewsPage.chooseDateStart(Utils.currentDate());
        filterNewsPage.chooseDateEnd(Utils.currentDate());
        filterNewsPage.cancelFilter();
        newsPage.newsPageElementIsVisible();
    }
}
