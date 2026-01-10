package ru.iteco.fmhandroid.ui.PageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static ru.iteco.fmhandroid.ui.Utils.Utils.waitDisplayed;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class FilterNewsPage {
    private final ViewInteraction category = onView(withId(R.id.news_item_category_text_auto_complete_text_view));
    private final ViewInteraction dateStart = onView(withId(R.id.news_item_publish_date_start_text_input_edit_text));
    private final ViewInteraction dateEnd = onView(withId(R.id.news_item_publish_date_end_text_input_edit_text));
    private final ViewInteraction filterButton = onView(withId(R.id.filter_button));
    private final ViewInteraction cancelButton = onView(withId(R.id.cancel_button));

    private final int filter = R.id.filter_button;

    public int getFilter() {
        return filter;
    }

    public void chooseCategory(String text) {
        Allure.step("Выбор категории");
        category.check(matches(isDisplayed()));
        category.perform(replaceText(text),
                closeSoftKeyboard());
    }

    public void chooseDateStart(String dateFrom) {
        Allure.step("Выбор первоначальной даты");
        dateStart.check(matches(isDisplayed()));
        dateStart.perform(replaceText(dateFrom),
                closeSoftKeyboard());
    }

    public void chooseDateEnd(String dateTo) {
        Allure.step("Выбор конечной даты");
        dateEnd.check(matches(isDisplayed()));
        dateEnd.perform(replaceText(dateTo),
                closeSoftKeyboard());
    }

    public void applyFilter() {
        Allure.step("Нажатие на кнопку 'Фильтровать'");
        onView(isRoot()).perform(waitDisplayed(R.id.filter_button, 7000));
        filterButton.check(matches(isDisplayed()));
        filterButton.perform(click());
    }

    public void cancelFilter() {
        Allure.step("Нажатие на кнопку 'Отмена'");
        onView(isRoot()).perform(waitDisplayed(R.id.cancel_button, 7000));
        cancelButton.check(matches(isDisplayed()));
        cancelButton.perform(click());
    }
}
