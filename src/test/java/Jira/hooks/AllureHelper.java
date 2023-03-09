package Jira.hooks;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;

import java.io.ByteArrayInputStream;

public class AllureHelper {
    public static void takeScreenshot(String methodName) {
        byte[] screenshot = Selenide.screenshot(OutputType.BYTES);
        Allure.addAttachment(methodName, new ByteArrayInputStream(screenshot));
    }

}
