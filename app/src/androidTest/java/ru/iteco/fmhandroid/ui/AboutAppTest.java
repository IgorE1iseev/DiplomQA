package ru.iteco.fmhandroid.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.Feature;
import ru.iteco.fmhandroid.ui.PageObject.AboutAppPage;
import ru.iteco.fmhandroid.ui.PageObject.AppBar;
import ru.iteco.fmhandroid.ui.PageObject.MainPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)

@Epic(value = "Раздел 'О приложении'")

public class AboutAppTest {
    AppBar appBar = new AppBar();
    MainPage mainPage = new MainPage();
    SuccessfulAuthorization successfulAuthorization = new SuccessfulAuthorization();
    AboutAppPage aboutAppPage = new AboutAppPage();

//    Сохраняем ссылки
    String privacyUrl = "https://vhospice.org/#/privacy-policy";
    String termsUrl = "https://vhospice.org/#/terms-of-use";
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setUp() {
        onView(isRoot()).perform(Utils.waitDisplayed(appBar.getAppBarContainer(), 10000));
        if (!mainPage.mainPageIsDisplayed()) {
            successfulAuthorization.userAuthorization();
        }
    }
//    При нажатии на ссылку просто пустой экран, за который не зацепиться при проверке
    @Feature(value = "Переход по ссылке 'Политика конфиденциальности'")
    @Test
    public void openPrivacyPolicy() {
        appBar.goToAboutApp();
        aboutAppPage.goToPrivacyPolicy(privacyUrl);
        aboutAppPage.clickToBackButton();
    }
    //    При нажатии на ссылку просто пустой экран, за который не зацепиться при проверке
    @Feature(value = "Переход по ссылке 'Пользовательское соглашение'")
    @Test
    public void openTermsOfUse() {
        appBar.goToAboutApp();
        aboutAppPage.goToTermsOfUse(termsUrl);
        aboutAppPage.clickToBackButton();
    }

    @Feature(value = "Возврат кнопкой 'Назад' из раздела 'О приложении'")
    @Test
    public void clickBackFromAboutApp() {
        appBar.goToAboutApp();
        aboutAppPage.clickToBackButton();
    }
}
