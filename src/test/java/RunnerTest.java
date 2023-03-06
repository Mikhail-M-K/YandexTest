import com.codeborne.selenide.logevents.SelenideLogger;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.runner.RunWith;

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
