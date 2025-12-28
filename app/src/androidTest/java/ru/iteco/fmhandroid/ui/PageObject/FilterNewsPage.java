package ru.iteco.fmhandroid.ui.PageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static ru.iteco.fmhandroid.ui.Utils.waitDisplayed;

import androidx.test.espresso.ViewInteraction;
import io.qameta.allure.kotlin.Step;

import ru.iteco.fmhandroid.R;

public class FilterNewsPage {
    private final ViewInteraction category = onView(withId(R.id.news_item_category_text_auto_complete_text_view));
    private final ViewInteraction dateStart = onView(withId(R.id.news_item_publish_date_start_text_input_edit_text));
    private final ViewInteraction dateEnd = onView(withId(R.id.news_item_publish_date_end_text_input_edit_text));
    private final ViewInteraction filterButton = onView(withId(R.id.filter_button));
    private final ViewInteraction cancelButton = onView(withId(R.id.cancel_button));

//    private final int cancel = R.id.cancel_button;
//    public int getCancel() {
//        return cancel;
//    }
    private final int filter = R.id.filter_button;
    public int getFilter() {
        return filter;
    }

    @Step("Выбор категории")
    public void chooseCategory(String text) {
        category.check(matches(isDisplayed()));
        category.perform(replaceText(text),
                closeSoftKeyboard());
    }
    @Step("Выбор первоначальной даты")
    public void chooseDateStart(String dateFrom) {
        dateStart.check(matches(isDisplayed()));
        dateStart.perform(replaceText(dateFrom),
                closeSoftKeyboard());
    }
    @Step("Выбор конечной даты")
    public void chooseDateEnd(String dateTo) {
        dateEnd.check(matches(isDisplayed()));
        dateEnd.perform(replaceText(dateTo),
                closeSoftKeyboard());
    }
    @Step("Нажатие на кнопку 'Фильтровать'")
    public void applyFilter() {
        onView(isRoot()).perform(waitDisplayed(R.id.filter_button, 7000));
        filterButton.check(matches(isDisplayed()));
        filterButton.perform(click());
    }
    @Step("Нажатие на кнопку 'Отмена'")
    public void cancelFilter() {
        onView(isRoot()).perform(waitDisplayed(R.id.cancel_button, 7000));
        cancelButton.check(matches(isDisplayed()));
        cancelButton.perform(click());
    }
}
