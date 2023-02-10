package Jira.pageElements;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class NavigationBar {
    public static SelenideElement btnTasks = $x("//a[contains(text(),'Задачи')]").as("Кнопка Задачи");
    public static SelenideElement btnTaskSearch = $x("//a[contains(text(),'Поиск задач')]").as("Кнопка Поиск задач");
    public static SelenideElement btnCreate = $x("//a[contains(text(),'Создать')]").as("Кнопка Создать");
    public static SelenideElement btnProjects = $x("//a[contains(text(),'Проекты')]").as("Кнопка Проекты");
    public static SelenideElement btnProjectTest = $x("//a[contains(text(),'Test (TEST)')]").as("Кнопка Проекты");
    public static SelenideElement btnMyBug = $x("//a[contains(text(),'Сообщенные мной')]").as("Кнопка Сообщенные мной");
    public static SelenideElement imgProfile = $x("//a[@id='header-details-user-fullname']").as("Иконка профиля");
    public static SelenideElement btnProfile = $x("//a[contains(text(),'Профиль')]").as("Кнопка Профиль");




    public static void openAllTasks(){
        btnTasks.click();
        btnTaskSearch.click();
    }

    public static void openSiteProfile(){
        imgProfile.click();
        btnProfile.click();
    }

    public static void openMyBag(){
        btnTasks.click();
        btnMyBug.click();
    }



}
