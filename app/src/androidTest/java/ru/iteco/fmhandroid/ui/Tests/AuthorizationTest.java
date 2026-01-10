package ru.iteco.fmhandroid.ui.Tests;


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
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.PageObject.AppBar;
import ru.iteco.fmhandroid.ui.PageObject.AuthorizationPage;
import ru.iteco.fmhandroid.ui.PageObject.MainPage;
import ru.iteco.fmhandroid.ui.Utils.TestData;
import ru.iteco.fmhandroid.ui.Utils.Utils;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)

@Epic(value = "Авторизация пользователя")

public class AuthorizationTest {
    AuthorizationPage authorizationPage = new AuthorizationPage();
    AppBar appBar = new AppBar();
    MainPage mainPage = new MainPage();


    @Rule
    //Запускает указанную AppActivity перед каждым тестом. Закрывает её после завершения теста
    //Обеспечивает изолированность тестов
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    //Ждём появления верхней панели AppBar на экране 15 секунд. Логаут, если уже отображается mainPage
    public void setUp() {
        Espresso.onView(isRoot()).perform(Utils.waitDisplayed(appBar.getAppBarContainer(), 15000));
        if (mainPage.mainPageIsDisplayed()) {
            appBar.logOut();
        }
    }

    @Feature(value = "Валидная авторизация")
    @Test
    public void validAuthorization() {
        authorizationPage.authorizationVisibility();
        authorizationPage.enterLogin(TestData.VALID_LOGIN);
        authorizationPage.enterPassword(TestData.VALID_PASSWORD);
        authorizationPage.clickEnterButton();
        mainPage.mainPageIsDisplayed();
    }

    @Feature(value = "Невалидная авторизация")
    @Test
    public void invalidAuthorization() {
        authorizationPage.authorizationVisibility();
        authorizationPage.enterLogin(TestData.INVALID_LOGIN);
        authorizationPage.enterPassword(TestData.INVALID_PASSWORD);
        authorizationPage.clickEnterButton();
        authorizationPage.authorizationVisibility();
//      оставлена видимость авторизации, потому что не находит поп-ап на экране.
//      Элемент скрыт, не получается найти.
    }

    @Feature(value = "Авторизация с пустыми полями ввода")
    @Test
    public void emptyFieldsAuthorization() {
        authorizationPage.authorizationVisibility();
        authorizationPage.enterLogin(TestData.EMPTY_DATA);
        authorizationPage.enterPassword(TestData.EMPTY_DATA);
        authorizationPage.clickEnterButton();
        authorizationPage.authorizationVisibility();

    }

    @Feature(value = "Ввод спецcимволов при авторизации")
    @Test
    public void specialSymbolsAuthorization() {
        authorizationPage.authorizationVisibility();
        authorizationPage.enterLogin(TestData.INVALID_LOGIN_SYMBOLS);
        authorizationPage.enterPassword(TestData.INVALID_PASSWORD_SYMBOLS);
        authorizationPage.clickEnterButton();
        authorizationPage.authorizationVisibility();

    }

    @Feature(value = "Ввод кириллицы при авторизации")
    @Test
    public void cyrillicAuthorization() {
        authorizationPage.authorizationVisibility();
        authorizationPage.enterLogin(TestData.INVALID_LOGIN_CYRILLIC);
        authorizationPage.enterPassword(TestData.INVALID_PASSWORD_CYRILLIC);
        authorizationPage.clickEnterButton();
        authorizationPage.authorizationVisibility();

    }
}



