package Hooks;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

public class ApiHooks implements BeforeAllCallback {
    @Override
    public void beforeAll(ExtensionContext context) {
            RestAssured.filters(new AllureRestAssured());
    }

}
