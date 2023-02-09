package Jira.pageElements;

import com.codeborne.selenide.SelenideElement;

import static Jira.pageElements.PopupCreate.btnConfirmError;
import static Jira.pageElements.PopupCreate.btnExecutedError;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.sleep;

public class BoardPage {
    public static SelenideElement valueTaskAll = $x("//span[contains(text(),'Показаны результаты:')]/child::span[3]").as("Количество задач всего");
    public static SelenideElement btnChangeView = $x("//button[@id='layout-switcher-button']").as("Кнопка Изменить вид");
    public static SelenideElement btnList = $x("//div[@id='layout-switcher-options']//a[contains(text(),'Список')]");
    public static SelenideElement btnView = $x("//div[@id='layout-switcher-options']//a[contains(text(),'Детальный просмотр')]");
    public static SelenideElement searchInput = $x("//input[@placeholder='Содержит текст']");
    public static SelenideElement btnSearch = $x("//button[contains(text(),'Поиск')]").as("Кнопка Поиск");
    public static SelenideElement header = $x("//h1[@id='summary-val']").as("Заголовок");
    public static SelenideElement statusText = $x("//strong[contains(text(),'Статус:')]/following-sibling::span").as("Вывод статуса");
    public static SelenideElement versionText = $x("//strong[@title='Затронуты версии']/following-sibling::span").as("Привязка в затронутой версии");
    public static SelenideElement btnInWork = $x("//span[contains(text(),'В работе')]").as("Статус В работе");
    public static SelenideElement btnBusinessProcess = $x("//span[contains(text(),'Бизнес-процесс')]").as("Выбор статуса Бизнес-процесс");
    public static SelenideElement btnDone = $x("//span[contains(text(),'Выполнено')]/parent::a").as("Выбор статуса Выполнено");
    public static SelenideElement btnExecuted = $x("//span[contains(text(),'Исполнено')]/parent::a").as("Выбор статуса Исполнено");
    public static SelenideElement btnConfirm = $x("//span[contains(text(),'Подтверждено')]/parent::a").as("Кнопка Подтверждено");

    public static void changeViewList(){
        btnChangeView.click();
        btnList.click();
    }
    public static void changeView(){
        btnChangeView.click();
        btnView.click();
    }


    public static void searchTest(String nameTest){
        searchInput.sendKeys(nameTest);
        btnSearch.click();
        sleep(2000);
    }

    public static void selectExecuted(){
        btnBusinessProcess.click();
        sleep(1000);
        btnBusinessProcess.click();
        btnExecuted.click();
        btnExecutedError.click();

    }

    public static void selectConfirm(){
        btnBusinessProcess.click();
        sleep(1000);
        btnBusinessProcess.click();
        btnConfirm.click();
        btnConfirmError.click();
    }
}