package Jira.hooks;

import org.junit.*;

import Jira.pageElements.LoginPage;
import Jira.ConfProperties;
import static com.codeborne.selenide.Selenide.*;
import static Jira.pageElements.LoginPage.*;


public class WebHooks {
    public static LoginPage loginPage;
    @Before
    public void authenticationProcess() {
        open("https://edujira.ifellow.ru/");
        authentication(ConfProperties.getProperty("login"), ConfProperties.getProperty("password"));
        btnEnter.click();
    }

    @After
    public void afterTest() {

    }
}
