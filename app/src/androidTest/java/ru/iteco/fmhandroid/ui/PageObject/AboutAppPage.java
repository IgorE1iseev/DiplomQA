package ru.iteco.fmhandroid.ui.PageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.intent.Intents.intended;

import android.content.Intent;

import androidx.test.espresso.intent.Intents;

import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;

public class AboutAppPage {
//    Кнопка "стрелка назад"
    int buttonArrowBack = R.id.about_back_image_button;
    public int getButtonArrowBack() {
        return buttonArrowBack;
    }
    @Step("Переход в 'Политика конфиденциальности'")
    public void goToPrivacyPolicy(String url) {
//        Активирует перехват Intent'ов
        Intents.init();
//        Нажимает на ссылку
        onView(withId(R.id.about_privacy_policy_value_text_view)).perform(click());
//        Проверяет действие
        intended(hasAction(Intent.ACTION_VIEW));
//        Проверяет URL
        intended(hasData(url));
//        Завершает перехват
        Intents.release();
    }
    @Step("Переход в 'Пользовательское соглашение'")
    public void goToTermsOfUse(String url) {
//        Активирует перехват Intent'ов
        Intents.init();
//        Нажимает на ссылку
        onView(withId(R.id.about_terms_of_use_value_text_view)).perform(click());
//        Проверяет действие
        intended(hasAction(Intent.ACTION_VIEW));
//        Проверяет URL
        intended(hasData(url));
//        Завершает перехват
        Intents.release();
    }
    @Step("Нажатие на кнопку-стрелку 'Назад'")
    public void clickToBackButton() {
        onView(withId(buttonArrowBack)).perform(click());
    }
}
