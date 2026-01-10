package ru.iteco.fmhandroid.ui.PageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.content.Intent;

import androidx.test.espresso.intent.Intents;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class AboutAppPage {
    //    Кнопка "стрелка назад"
    int buttonArrowBack = R.id.about_back_image_button;

    public int getButtonArrowBack() {
        return buttonArrowBack;
    }

    public void goToPrivacyPolicy(String url) {
        Allure.step("Переход в 'Политика конфиденциальности'");
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

    public void goToTermsOfUse(String url) {
        Allure.step("Переход в 'Пользовательское соглашение'");
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

    public void clickToBackButton() {
        Allure.step("Нажатие на кнопку-стрелку 'Назад'");
        onView(withId(buttonArrowBack)).perform(click());
    }
}
