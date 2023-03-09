package Hooks;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import io.qameta.allure.selenide.AllureSelenide;

import static JiraSteps.AuthenticationSteps.authentication;
import static com.codeborne.selenide.Selenide.open;

public class WebHooks {
    @Before
    public void beforeEach() {
        Configuration.startMaximized = true;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));

        open("https://edujira.ifellow.ru/");
        authentication("myasnikovm", "Qwerty123");
        Allure.addAttachment("Логин для входа", "myasnikovm");
        Allure.addAttachment("Пароль для входа", "Qwerty123");
    }

    @After
    public void afterTest(Scenario scenario) {
        if (scenario.isFailed()) {
            AllureHelper.createScreenshot(scenario.getName());
        }
        WebDriverRunner.closeWebDriver();
    }
}
