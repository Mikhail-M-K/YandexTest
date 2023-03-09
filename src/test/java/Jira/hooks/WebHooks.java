package Jira.hooks;

import Jira.ConfProperties;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.After;
import org.junit.Before;

import static Jira.steps.AuthenticationSteps.authentication;
import static Jira.pageElements.LoginPage.btnEnter;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;


public class WebHooks {
    @Before
    public void authenticationProcess() {
        setupAllure();
        Configuration.startMaximized = true;
        open("https://edujira.ifellow.ru/");
        authentication(ConfProperties.getProperty("login"), ConfProperties.getProperty("password"));
        Allure.addAttachment("Логин для входа", ConfProperties.getProperty("login"));
        Allure.addAttachment("Пароль для входа", ConfProperties.getProperty("password"));
        btnEnter.click();
    }

    public static void setupAllure() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false)
        );
    }

    @After
    public void afterTest(Scenario scenario) {
        if (scenario.isFailed()) {
            AllureHelper.takeScreenshot(scenario.getName());
        }
        closeWebDriver();
    }
}
