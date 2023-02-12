package Jira.pageElements;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class BoardPage {
    public static SelenideElement valueTaskAll = $x("//span[contains(text(),'Показаны результаты:')]/child::span[3]").as("Количество задач всего");
    public static SelenideElement btnChangeView = $x("//button[@id='layout-switcher-button']").as("Кнопка Изменить вид");
    public static SelenideElement searchInput = $x("//input[@placeholder='Содержит текст']");
    public static SelenideElement btnSearch = $x("//button[contains(text(),'Поиск')]").as("Кнопка Поиск");
    public static SelenideElement header = $x("//h1[@id='summary-val']").as("Заголовок");
    public static SelenideElement statusText = $x("//strong[contains(text(),'Статус:')]/following-sibling::span").as("Вывод статуса");
    public static SelenideElement versionText = $x("//strong[@title='Затронуты версии']/following-sibling::span").as("Привязка в затронутой версии");
    public static SelenideElement btnInWork = $x("//span[contains(text(),'В работе')]").as("Статус В работе");
    public static SelenideElement btnBusinessProcess = $x("//span[contains(text(),'Бизнес-процесс')]").as("Выбор статуса Бизнес-процесс");
    public static SelenideElement infoEnvironment = $x("//div[@id = 'environment-val']/child::p").as("Окружение");
    public static SelenideElement infoDescription = $x("//div[@id = 'description-val']//child::p").as("Описание");

    public static SelenideElement btnListView(String view) {
        return $x("//div[@id='layout-switcher-options']//a[contains(text(),'" + view + "')]").as("Вид списка: " + view);
    }

    public static SelenideElement btnStatusBug (String status) {
        return $x("//span[contains(text(),'" + status + "')]/parent::a").as("Статус: " + status);
    }
}