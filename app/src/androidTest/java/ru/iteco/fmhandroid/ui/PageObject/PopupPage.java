package ru.iteco.fmhandroid.ui.PageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import io.qameta.allure.kotlin.Allure;

public class PopupPage {

    public void popupSomethingWrongVisibility() {
        Allure.step("Видимость поп-ап ошибки 'Что-то пошло не так. Попробуйте позднее.'");
        onView(withText("Что-то пошло не так. Попробуйте позднее."))
                .check(matches(isDisplayed()));
    }

    public void popupEmptyFieldsVisibility() {
        Allure.step("Видимость поп-ап ошибки 'Логин и пароль не могут быть пустыми'");
        onView(withText("Логин и пароль не могут быть пустыми")).check(matches(isDisplayed()));
    }
}
