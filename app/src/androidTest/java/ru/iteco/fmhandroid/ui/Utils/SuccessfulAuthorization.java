package ru.iteco.fmhandroid.ui.Utils;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.PageObject.AppBar;
import ru.iteco.fmhandroid.ui.PageObject.AuthorizationPage;
import ru.iteco.fmhandroid.ui.PageObject.MainPage;

public class SuccessfulAuthorization {
    AuthorizationPage authorizationPage = new AuthorizationPage();
    MainPage mainPage = new MainPage();
    AppBar appBar = new AppBar();

    public void userAuthorization() {
        Allure.step("Пользователь авторизуется");
        authorizationPage.enterLogin(TestData.VALID_LOGIN);
        authorizationPage.enterPassword(TestData.VALID_PASSWORD);
        authorizationPage.clickEnterButton();
        onView(isRoot()).perform(Utils.waitDisplayed(appBar.getAuthorizationButton(), 7000));
        mainPage.newsPageIsDisplayed();
    }
}
