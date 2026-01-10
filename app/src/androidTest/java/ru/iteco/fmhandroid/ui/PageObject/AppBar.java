package ru.iteco.fmhandroid.ui.PageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.Utils.Utils;

public class AppBar {

    MainPage mainPage = new MainPage();
    NewsPage newsPage = new NewsPage();
    AboutAppPage aboutAppPage = new AboutAppPage();
    OurMissionPage ourMissionPage = new OurMissionPage();


    int appBarContainer = R.id.container_custom_app_bar_include_on_fragment_main;
    public int getAppBarContainer() {
        return appBarContainer;
    }
    //Получение кнопки профиля в углу экрана
    int authorizationButton = R.id.authorization_image_button;
    public int getAuthorizationButton() {
        return authorizationButton;
    }

    public ViewInteraction buttonMenu = onView(withId(R.id.main_menu_image_button));

    public ViewInteraction buttonOurMission = onView(withId(R.id.our_mission_image_button));

    public ViewInteraction menuMain = onView(
            allOf(withId(android.R.id.title), withText("Главная")));
    public ViewInteraction menuNews = onView(
            allOf(withId(android.R.id.title), withText("Новости")));
    public ViewInteraction menuAboutApp = onView(
            allOf(withId(android.R.id.title), withText("О приложении")));

    public void goToNews() {
        Allure.step("Переход в раздел 'Новости'");
        buttonMenu.check(matches(isDisplayed()));
        buttonMenu.perform(click());
        menuNews.check(matches(isDisplayed()));
        menuNews.perform(click());
        onView(isRoot()).perform(Utils.waitDisplayed(newsPage.getContainerListPageNews(), 5000));
    }

    public void goToAboutApp() {
        Allure.step("Переход в раздел 'О приложении'");
        buttonMenu.check(matches(isDisplayed()));
        buttonMenu.perform(click());
        menuAboutApp.check(matches(isDisplayed()));
        menuAboutApp.perform(click());
        onView(isRoot()).perform(Utils.waitDisplayed(aboutAppPage.getButtonArrowBack(), 5000));
    }

    public void goToMain() {
        Allure.step("Переход в раздел 'Главная'");
        buttonMenu.check(matches(isDisplayed()));
        buttonMenu.perform(click());
        menuMain.check(matches(isDisplayed()));
        menuMain.perform(click());
        onView(isRoot()).perform(Utils.waitDisplayed(mainPage.getContainerListNews(), 5000));
    }

    public void goToOurMission() {
        Allure.step("Переход в раздел 'Главное-жить любя'");
        buttonOurMission.check(matches(isDisplayed()));
        buttonOurMission.perform(click());
        onView(isRoot()).perform(Utils.waitDisplayed(ourMissionPage.getOurMissionTitleText(), 5000));
    }

    public void logOut() {
        Allure.step("Выход из авторизованного состояния");
        ViewInteraction buttonProfile = onView(withId(R.id.authorization_image_button));
        buttonProfile.perform(click());
        ViewInteraction logOut = onView(withText("Выйти"));
        logOut.check(matches(isDisplayed())).perform(click());
    }
}

