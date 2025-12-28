package ru.iteco.fmhandroid.ui.PageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import io.qameta.allure.kotlin.Step;

public class PopupPage {

    @Step("Видимость поп-ап ошибки 'Что-то пошло не так. Попробуйте позднее.'")
    public void popupSomethingWrongVisibility() {
        onView(withText("Что-то пошло не так. Попробуйте позднее."))
                .check(matches(isDisplayed()));
    }

    @Step("Видимость поп-ап ошибки 'Логин и пароль не могут быть пустыми'")
    public void popupEmptyFieldsVisibility() {
        onView(withText("Логин и пароль не могут быть пустыми")).check(matches(isDisplayed()));
    }
}
