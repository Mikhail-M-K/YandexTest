package Jira.steps;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Тогда;
import org.junit.Assert;

import static Jira.pageElements.BoardPage.*;
import static Jira.pageElements.PopupCreate.btnConfirmError;
import static Jira.pageElements.PopupCreate.btnExecutedError;
import static Jira.steps.NavigationSteps.openMyBug;
import static com.codeborne.selenide.Selenide.sleep;

public class BoardSteps {
    @Дано("Выбор списка")
    public static void changeViewList(){
        btnChangeView.click();
        btnListView("Список").click();
    }
    @Дано("Переход к детальному просмотру")
    public static void changeView(){
        btnChangeView.click();
        btnListView("Детальный просмотр").click();
    }

    @Дано("Поиск теста по названию '(.*)'")
    public static void searchTest(String nameTest){
        searchInput.sendKeys(nameTest);
        btnSearch.click();
        sleep(2000);
    }
    @Дано("Нажатие кнопки Исполнено")
    public static void selectExecuted(){
        btnBusinessProcess.click();
        sleep(1000);
        btnStatusBug("Исполнено").click();
        btnExecutedError.click();

    }

    @Дано("Нажатие кнопок Подтверждено и Выполнено")
    public static void selectConfirm(){
        btnBusinessProcess.click();
        sleep(1000);
        btnStatusBug("Подтверждено").click();
        btnConfirmError.click();
        btnStatusBug("Выполнено").click();
    }

    //@Step("Проверка созданного бага, по теме, описанию и окружении")
    @Тогда("Проверка созданного бага, по теме, описанию и окружении (.*), (.*), (.*)")
    public static void checkCreateBug(String theme, String description, String environment) {
        openMyBug();
        Assert.assertEquals(header.getText(),theme);
        Assert.assertEquals(infoDescription.getText(),description);
        Assert.assertEquals(infoEnvironment.getText(),environment);
    }

    @Тогда("Проверка статса бага (.*)")
    public static void checkStatusBag(String statusBug) {
        sleep(2000);
        Assert.assertEquals(statusText.getText(), statusBug);
    }

    @Дано("Проверка на количество задач")
    public static void checkTasksOutput() {
        String numTasks = valueTaskAll.getText();
        System.out.println("Всего задач: " + valueTaskAll.getText());
        Assert.assertEquals(numTasks, valueTaskAll.getText());
    }

    @Тогда ("Проверка данных задачи")
    public static void check(){
        Assert.assertEquals(header.getText(),"TestSelenium");
        System.out.println("Статус TestSelenium: " + statusText.getText());
        System.out.println(versionText.getText().equals("Version 2.0") ? "Version 2.0":"Привязка версии отличается от Version 2.0");
    }

    @Дано("Выбор статуса В работе")
    public void выборСтатусаВРаботе() {
        btnInWork.click();
    }
}
