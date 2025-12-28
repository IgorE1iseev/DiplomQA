package ru.iteco.fmhandroid.ui.PageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;

public class OurMissionPage {
    private final int ourMissionTitleText = R.id.our_mission_title_text_view;

    public int getOurMissionTitleText() {
        return ourMissionTitleText;
    }

    @Step("Видимость страницы с цитатами")
    public void ourMissionIsDisplayed() {
        onView(withId(ourMissionTitleText)).check(matches(isDisplayed()));
        onView(withId(ourMissionTitleText)).check(matches(withText("Главное - жить любя")));
    }
}
