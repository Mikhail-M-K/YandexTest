package Jira.steps;

import io.qameta.allure.Step;
import org.junit.Assert;

import static Jira.pageElements.BoardPage.*;
import static Jira.pageElements.PopupCreate.btnConfirmError;
import static Jira.pageElements.PopupCreate.btnExecutedError;
import static Jira.steps.Navigation.openMyBug;
import static com.codeborne.selenide.Selenide.sleep;

public class BoardSteps {
    public static void changeViewList(){
        btnChangeView.click();
        btnListView("Список").click();
    }
    public static void changeView(){
        btnChangeView.click();
        btnListView("Детальный просмотр").click();
    }


    public static void searchTest(String nameTest){
        searchInput.sendKeys(nameTest);
        btnSearch.click();
        sleep(2000);
    }

    public static void selectExecuted(){
        btnBusinessProcess.click();
        sleep(1000);
        btnStatusBug("Исполнено").click();
        btnExecutedError.click();

    }

    public static void selectConfirm(){
        btnBusinessProcess.click();
        sleep(1000);
        btnStatusBug("Подтверждено").click();
        btnConfirmError.click();
        btnStatusBug("Выполнено").click();
    }

    @Step("Проверка созданного бага, по теме, описанию и окружении")
    public static void checkCreateBug(String theme, String description, String environment) {
        openMyBug();
        Assert.assertEquals(header.getText(),theme);
        Assert.assertEquals(infoDescription.getText(),description);
        Assert.assertEquals(infoEnvironment.getText(),environment);
    }

    public static void checkStatusBag(String statusBug) {
        sleep(2000);
        Assert.assertEquals(statusText.getText(), statusBug);
    }

}
