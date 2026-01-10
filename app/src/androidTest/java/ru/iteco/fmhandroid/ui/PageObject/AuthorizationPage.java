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

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;


public class AuthorizationPage {
    public void authorizationVisibility() {
        Allure.step("Наличие заголовка 'Авторизация'");
        ViewInteraction authorizationVisibility = onView(withText("Авторизация"));
        authorizationVisibility.check(matches(isDisplayed()));
    }

    public void enterLogin(String login) {
        Allure.step("Ввод логина");
        ViewInteraction enterLogin = onView(withHint("Логин"));
        enterLogin.perform(replaceText(login));
        closeSoftKeyboard();
    }

    public void enterPassword(String password) {
        Allure.step("Ввод пароля");
        ViewInteraction enterPassword = onView(withHint("Пароль"));
        enterPassword.perform(replaceText(password));
        closeSoftKeyboard();
    }

    public void clickEnterButton() {
        Allure.step("Нажатие кнопки 'Войти'");
        ViewInteraction clickEnterButton = onView(withId(R.id.enter_button));
        clickEnterButton.check(matches(isDisplayed()));
        clickEnterButton.perform(click());
    }
}