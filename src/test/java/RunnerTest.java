import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.qameta.allure.Allure;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.AfterClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = { "pretty", "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm"},
        features = "src/test/resources/features",
        glue = {"APISteps"},
        tags = "@Test",
        snippets = CucumberOptions.SnippetType.CAMELCASE
)

public class RunnerTest {
    public static void before(){
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide()
                        .screenshots(true)
                        .savePageSource(true)
        );
    }

}
