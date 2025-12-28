package ru.iteco.fmhandroid.ui.PageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static ru.iteco.fmhandroid.ui.Utils.waitDisplayed;

import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;
import androidx.test.espresso.ViewInteraction;


public class CreateNewsPage {
    private final ViewInteraction categoryCreateNews = onView(withId(R.id.news_item_category_text_auto_complete_text_view));
    private final ViewInteraction titleCreateNews = onView(withId(R.id.news_item_title_text_input_edit_text));
    private final ViewInteraction dateCreateNews = onView(withId(R.id.news_item_publish_date_text_input_edit_text));
    private final ViewInteraction timeCreateNews = onView(withId(R.id.news_item_publish_time_text_input_edit_text));
    private final ViewInteraction descriptionCreateNews = onView(withId(R.id.news_item_description_text_input_edit_text));
    private final int saveButtonCreateNews = R.id.save_button;

    public int getSaveButtonCreateNews() {
        return saveButtonCreateNews;
    }
    private final int containerCreateNews = R.id.container_custom_app_bar_include_on_fragment_create_edit_news;

    @Step("Выбор категории в создании новости")
    public void chooseCategoryCreateNews(String text) {
        categoryCreateNews.check(matches(isDisplayed()));
        categoryCreateNews.perform(replaceText(text), closeSoftKeyboard());
    }
    @Step("Ввод заголовка в создании новости")
    public void enterTitleCreateNews(String text) {
        titleCreateNews.check(matches(isDisplayed()));
        titleCreateNews.perform(replaceText(text), closeSoftKeyboard());
    }
    @Step("Ввод даты публикации в создании новости")
    public void enterDateCreateNews(String text) {
        dateCreateNews.check(matches(isDisplayed()));
        dateCreateNews.perform(replaceText(text), closeSoftKeyboard());
    }
    @Step("Ввод времени публикации в создании новости")
    public void enterTimeCreateNews(String text) {
        timeCreateNews.check(matches(isDisplayed()));
        timeCreateNews.perform(replaceText(text), closeSoftKeyboard());
    }
    @Step("Ввод описания в создании новости")
    public void enterDescriptionCreateNews(String text) {
        descriptionCreateNews.check(matches(isDisplayed()));
        descriptionCreateNews.perform(replaceText(text), closeSoftKeyboard());
    }
//    Дожидается появление кнопки, скроллит экран ниже и нажимает
    @Step("Нажатие на кнопку 'Сохранить'")
    public void saveNewsButton() {
        onView(isRoot()).perform(waitDisplayed(saveButtonCreateNews, 4000));
        onView(withId(R.id.save_button))
                .check(matches(isDisplayed()))
                .perform(scrollTo(), click());
    }
    @Step("Видимость открытой страницы 'Создание новости'")
    public void newsCreationFormIsVisible() {
        onView(withId(containerCreateNews)).check(matches(isDisplayed()));
    }
}
