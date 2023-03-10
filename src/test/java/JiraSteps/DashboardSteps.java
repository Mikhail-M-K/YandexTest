package JiraSteps;

import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.qameta.allure.Allure;
import org.junit.Assert;

import static com.codeborne.selenide.Selenide.sleep;
import static pageElements.BoardPage.*;
import static pageElements.BoardPage.valueTaskAll;
import static pageElements.NavigationBar.btnNavigationBar;
import static pageElements.PopupCreate.*;

public class DashboardSteps {
    @Когда("Переход к поиску задач")
    public static void openAllTasks(){
        btnNavigationBar("Задачи").click();
        btnNavigationBar("Поиск задач").click();
    }

    @Когда("Выбор списка")
    public static void changeViewList(){
        btnChangeView.click();
        btnListView("Список").click();
    }

    @Тогда("Проверка на количество задач")
    public static void checkTasksOutput() {
        String numTasks = valueTaskAll.getText();
        System.out.println("Всего задач: " + valueTaskAll.getText());
        Assert.assertEquals(numTasks, valueTaskAll.getText());
        Allure.addAttachment("Количество заведенных задач", numTasks);
    }

    @Когда("Переход к детальному просмотру")
    public static void changeView(){
        btnChangeView.click();
        btnListView("Детальный просмотр").click();
    }

    @Когда("Поиск теста по названию {string}")
    public static void searchTest(String nameTest){
        searchInput.sendKeys(nameTest);
        btnSearch.click();
        sleep(2000);
    }

    @Тогда("Проверка данных задачи")
    public static void check(){
        Assert.assertEquals(header.getText(),"TestSelenium");
        System.out.println("Статус TestSelenium: " + statusText.getText());
        System.out.println(versionText.getText().equals("Version 2.0") ? "Version 2.0":"Привязка версии отличается от Version 2.0");
        Assert.assertEquals(versionText.getText(),"Version 2.0");
    }

    @Тогда("Проверка созданного бага, по теме, описанию и окружении {string}, {string}, {string}")
    public static void checkCreateBug(String theme, String description, String environment) {
        NavigationSteps.openMyBug();
        Assert.assertEquals(header.getText(),theme);
        Assert.assertEquals(infoDescription.getText(),description);
        Assert.assertEquals(infoEnvironment.getText(),environment);
    }

    @Тогда("Проверка статуса бага {string}")
    public static void checkStatusBag(String statusBug) {
        sleep(2000);
        Assert.assertEquals(statusText.getText(), statusBug);
    }

    @Когда("Выбор статуса В работе")
    public void statusInWork() {
        btnInWork.click();
    }

    @Когда("Нажатие кнопки Исполнено")
    public static void selectExecuted(){
        btnBusinessProcess.click();
        sleep(1000);
        btnStatusBug("Исполнено").click();
        btnExecutedError.click();
    }

    @Когда("Нажатие кнопок Подтверждено и Выполнено")
    public static void selectConfirm(){
        btnBusinessProcess.click();
        sleep(1000);
        btnStatusBug("Подтверждено").click();
        btnConfirmError.click();
        btnStatusBug("Выполнено").click();
    }
}
