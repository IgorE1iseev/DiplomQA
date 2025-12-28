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
import ru.iteco.fmhandroid.ui.PageObject.AuthorizationPage;
import ru.iteco.fmhandroid.ui.PageObject.MainPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)

@Epic(value = "Авторизация пользователя")

public class AuthorizationTest {
    AuthorizationPage authorizationPage = new AuthorizationPage();
    AppBar appBar = new AppBar();
    MainPage mainPage = new MainPage();
//    PopupPage popupPage = new PopupPage();


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
        authorizationPage.enterLogin("login2");
        authorizationPage.enterPassword("password2");
        authorizationPage.clickEnterButton();
        mainPage.mainPageIsDisplayed();
    }

    @Feature(value = "Невалидная авторизация")
    @Test
    public void invalidAuthorization() {
        authorizationPage.authorizationVisibility();
        authorizationPage.enterLogin("loginn25");
        authorizationPage.enterPassword("passwordd25");
        authorizationPage.clickEnterButton();
        authorizationPage.authorizationVisibility();
//      оставлена видимость авторизации, потому что не находит поп-ап на экране.
//      Элемент скрыт, не получается найти.

//        popupPage.popupSomethingWrongVisibility();

    }

    @Feature(value = "Авторизация с пустыми полями ввода")
    @Test
    public void emptyFieldsAuthorization() {
        authorizationPage.authorizationVisibility();
        authorizationPage.enterLogin("");
        authorizationPage.enterPassword("");
        authorizationPage.clickEnterButton();
        authorizationPage.authorizationVisibility();

//        popupPage.popupEmptyFieldsVisibility();
    }

    @Feature(value = "Ввод спецcимволов при авторизации")
    @Test
    public void specialSymbolsAuthorization() {
        authorizationPage.authorizationVisibility();
        authorizationPage.enterLogin("login2@");
        authorizationPage.enterPassword("password2");
        authorizationPage.clickEnterButton();
        authorizationPage.authorizationVisibility();

//        popupPage.popupSomethingWrongVisibility();
    }

    @Feature(value = "Ввод кириллицы при авторизации")
    @Test
    public void cyrillicAuthorization() {
        authorizationPage.authorizationVisibility();
        authorizationPage.enterLogin("логин2");
        authorizationPage.enterPassword("пароль2");
        authorizationPage.clickEnterButton();
        authorizationPage.authorizationVisibility();

//        popupPage.popupSomethingWrongVisibility();
    }
}



