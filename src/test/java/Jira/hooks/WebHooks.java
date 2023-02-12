package Jira.hooks;

import Jira.ConfProperties;
import com.codeborne.selenide.Configuration;
import org.junit.After;
import org.junit.Before;

import static Jira.steps.Authentication.authentication;
import static Jira.pageElements.LoginPage.btnEnter;
import static com.codeborne.selenide.Selenide.open;


public class WebHooks {
    @Before
    public void authenticationProcess() {
        Configuration.startMaximized = true;
        open("https://edujira.ifellow.ru/");
        authentication(ConfProperties.getProperty("login"), ConfProperties.getProperty("password"));
        btnEnter.click();
    }

    @After
    public void afterTest() {

    }
}
