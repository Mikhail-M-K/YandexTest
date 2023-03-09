package Hooks;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;

import java.io.ByteArrayInputStream;

public class AllureHelper {
    public static void createScreenshot(String nameScreenshot) {
        byte[] screen = Selenide.screenshot(OutputType.BYTES);
        Allure.addAttachment(nameScreenshot, new ByteArrayInputStream(screen));
    }
}
