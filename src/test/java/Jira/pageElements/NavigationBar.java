package Jira.pageElements;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class NavigationBar {
    public static SelenideElement btnNavigationBar(String nameBtn){
       return $x("//a[contains(text(),'" + nameBtn + "')]").as("Кнопка " + nameBtn + "");
    }
}
