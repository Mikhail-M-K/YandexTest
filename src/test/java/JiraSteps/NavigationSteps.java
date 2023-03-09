package JiraSteps;

import io.cucumber.java.ru.Когда;
import io.qameta.allure.Step;

import static pageElements.NavigationBar.btnNavigationBar;
import static pageElements.PopupCreate.*;

public class NavigationSteps {
    @Когда("Создание бага с навигационной панели {string}, {string}, {string}")
    public static void createBugNavigation(String theme, String description, String environment){
        btnNavigationBar("Создать").click();
        inputTheme.sendKeys(theme);
        inputDescription.sendKeys(description);
        btnText.scrollTo().click();
        selectVersion.click();
        forScroll.scrollTo();
        inputEnvironment.sendKeys(environment);
        btnCreateError.click();
    }

    @Step("Переход к сообщенным багам")
    @Когда("Переход к сообщенным багам")
    public static void openMyBug(){
        btnNavigationBar("Задачи").click();
        btnNavigationBar("Сообщенные мной").click();
    }

}
