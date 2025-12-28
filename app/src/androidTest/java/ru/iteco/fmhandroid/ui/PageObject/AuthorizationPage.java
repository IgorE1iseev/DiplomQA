package ru.iteco.fmhandroid.ui.PageObject;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;


public class AuthorizationPage {
    @Step("Наличие заголовка 'Авторизация'")
    public void authorizationVisibility() {
        ViewInteraction authorizationVisibility = onView(withText("Авторизация"));
        authorizationVisibility.check(matches(isDisplayed()));
    }

    @Step("Ввод логина")
    public void enterLogin(String login) {
        ViewInteraction enterLogin = onView(withHint("Логин"));
        enterLogin.perform(replaceText(login));
        closeSoftKeyboard();
    }

    @Step("Ввод пароля")
    public void enterPassword(String password) {
        ViewInteraction enterPassword = onView(withHint("Пароль"));
        enterPassword.perform(replaceText(password));
        closeSoftKeyboard();
    }

    @Step("Нажатие кнопки 'Войти'")
    public void clickEnterButton() {
        ViewInteraction clickEnterButton = onView(withId(R.id.enter_button));
        clickEnterButton.check(matches(isDisplayed()));
        clickEnterButton.perform(click());
    }
}